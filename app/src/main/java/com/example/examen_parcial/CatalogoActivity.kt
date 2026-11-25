package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CatalogoActivity : AppCompatActivity() {

    private data class ItemCotizacion(
        val nombre: String,
        val precioUnitario: Double,
        val cantidad: Int
    ) {
        val subtotal: Double get() = precioUnitario * cantidad
    }

    private val productosDisponibles = listOf(
        "Seleccione un producto" to 0.0,
        "Silla Ejecutiva ---- S/. 350" to 350.0,
        "Silla Gerencial ---- S/. 1200" to 1200.0,
        "Escritorio Ejecutivo ---- S/. 1800" to 1800.0,
        "Mesa de Reuniones ---- S/. 450" to 450.0,
        "Módulo de Recepción ---- S/. 600" to 600.0,
        "Butaca para Auditorio ---- S/. 250" to 250.0,
        "Archivador Metálico ---- S/. 1500" to 1500.0
    )

    private val carrito = mutableListOf<ItemCotizacion>()
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo)

        db = FirebaseFirestore.getInstance()

        val nombre = findViewById<EditText>(R.id.txtNombre)
        val correo = findViewById<EditText>(R.id.txtCorreo)
        val catalogo = findViewById<Spinner>(R.id.spnCatalogo)
        val cantidad = findViewById<EditText>(R.id.txtCantidad)
        val btnAgregar = findViewById<Button>(R.id.btnAgregarProducto)
        val tvListaProductos = findViewById<TextView>(R.id.tvListaProductos)

        val transporte = findViewById<CheckBox>(R.id.chkTransporte)
        val instalacion = findViewById<CheckBox>(R.id.chkInstalacion)
        val garantia = findViewById<CheckBox>(R.id.chkGarantia)

        val descuento = findViewById<SeekBar>(R.id.skDescuento)
        val lblDescuento = findViewById<TextView>(R.id.lblDescuento)
        val calificacion = findViewById<RatingBar>(R.id.rtbCalificacion)

        val cotizar = findViewById<Button>(R.id.btnCotizar)
        val limpiar = findViewById<Button>(R.id.btnLimpiar)
        val volver = findViewById<Button>(R.id.btnVolver)

        // Cargar productos en el Spinner
        val nombresSpinner = productosDisponibles.map { it.first }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, nombresSpinner)
        catalogo.adapter = adapter

        fun actualizarVistaCarrito() {
            if (carrito.isEmpty()) {
                tvListaProductos.text = "Ningún producto agregado aún."
            } else {
                val texto = StringBuilder()
                carrito.forEachIndexed { index, item ->
                    texto.append("${index + 1}. ${item.nombre} (x${item.cantidad}) = S/. ${String.format("%.2f", item.subtotal)}\n")
                }
                val acumulado = carrito.sumOf { it.subtotal }
                texto.append("\nSubtotal muebles: S/. ${String.format("%.2f", acumulado)}")
                tvListaProductos.text = texto.toString()
            }
        }

        // Botón "+ Agregar" para acumular varios productos
        btnAgregar.setOnClickListener {
            val posicion = catalogo.selectedItemPosition
            val cantTexto = cantidad.text.toString().trim()

            if (posicion == 0) {
                Toast.makeText(this, "Selecciona un producto del catálogo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cantNum = cantTexto.toIntOrNull()
            if (cantNum == null || cantNum <= 0) {
                Toast.makeText(this, "Ingresa una cantidad válida", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val (etiqueta, precio) = productosDisponibles[posicion]
            val nombreLimpio = etiqueta.substringBefore(" ----")

            carrito.add(ItemCotizacion(nombreLimpio, precio, cantNum))
            actualizarVistaCarrito()

            // Limpiar selector para agregar el siguiente producto
            catalogo.setSelection(0)
            cantidad.setText("")
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
        }

        // Mostrar porcentaje del SeekBar
        descuento.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                lblDescuento.text = "Descuento: $progress%"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Botón Cotizar (calcula todo y guarda en Firestore)
        cotizar.setOnClickListener {
            val clienteNombre = nombre.text.toString().trim()
            val clienteCorreo = correo.text.toString().trim()

            if (clienteNombre.isEmpty() || clienteCorreo.isEmpty()) {
                Toast.makeText(this, "Ingresa tu nombre y correo electrónico", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Si el usuario eligió un producto y olvidó pulsar "+ Agregar", lo añadimos automáticamente
            if (carrito.isEmpty() && catalogo.selectedItemPosition > 0) {
                val cantNum = cantidad.text.toString().trim().toIntOrNull() ?: 0
                if (cantNum > 0) {
                    val (etiqueta, precio) = productosDisponibles[catalogo.selectedItemPosition]
                    carrito.add(ItemCotizacion(etiqueta.substringBefore(" ----"), precio, cantNum))
                    actualizarVistaCarrito()
                }
            }

            if (carrito.isEmpty()) {
                Toast.makeText(this, "Agrega al menos un producto para cotizar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val subtotalProductos = carrito.sumOf { it.subtotal }
            val detalleProductos = carrito.joinToString("\n") {
                "• ${it.nombre} (x${it.cantidad} × S/. ${String.format("%.2f", it.precioUnitario)}) = S/. ${String.format("%.2f", it.subtotal)}"
            }

            val listaAdicionales = mutableListOf<String>()
            var montoAdicional = 0.0

            if (transporte.isChecked) {
                listaAdicionales.add("Transporte (+ S/. 50.00)")
                montoAdicional += 50.0
            }
            if (instalacion.isChecked) {
                listaAdicionales.add("Instalación (+ S/. 100.00)")
                montoAdicional += 100.0
            }
            if (garantia.isChecked) {
                listaAdicionales.add("Garantía Extendida (+ S/. 200.00)")
                montoAdicional += 200.0
            }

            val adicionalesTexto = if (listaAdicionales.isEmpty()) {
                "Ninguno"
            } else {
                listaAdicionales.joinToString("\n")
            }

            val subtotalConAdicionales = subtotalProductos + montoAdicional
            val porcentajeDescuento = descuento.progress
            val montoDescuento = subtotalConAdicionales * porcentajeDescuento / 100.0
            val total = subtotalConAdicionales - montoDescuento
            val estrellas = calificacion.rating

            // Guardar cotización en Cloud Firestore
            val cotizacionMap = hashMapOf(
                "uidUsuario" to (FirebaseAuth.getInstance().currentUser?.uid ?: "anonimo"),
                "cliente" to clienteNombre,
                "correo" to clienteCorreo,
                "productos" to carrito.map {
                    mapOf(
                        "nombre" to it.nombre,
                        "precioUnitario" to it.precioUnitario,
                        "cantidad" to it.cantidad,
                        "subtotal" to it.subtotal
                    )
                },
                "subtotalProductos" to subtotalProductos,
                "adicionales" to listaAdicionales,
                "montoAdicional" to montoAdicional,
                "descuentoPorcentaje" to porcentajeDescuento,
                "montoDescuento" to montoDescuento,
                "total" to total,
                "calificacion" to estrellas,
                "fecha" to Timestamp.now()
            )

            db.collection("cotizaciones")
                .add(cotizacionMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "Cotización guardada en Firebase", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al guardar en Firebase: ${e.message}", Toast.LENGTH_SHORT).show()
                }

            // Abrir pantalla de resultado
            val intent = Intent(this, ResultadoActivity::class.java).apply {
                putExtra("nombre", clienteNombre)
                putExtra("correo", clienteCorreo)
                putExtra("detalleProductos", detalleProductos)
                putExtra("subtotal", subtotalProductos)
                putExtra("adicionales", adicionalesTexto)
                putExtra("montoAdicional", montoAdicional)
                putExtra("subtotal2", subtotalConAdicionales)
                putExtra("descuento", porcentajeDescuento)
                putExtra("montoDescuento", montoDescuento)
                putExtra("total", total)
                putExtra("calificacion", estrellas)
            }
            startActivity(intent)
        }

        limpiar.setOnClickListener {
            nombre.setText("")
            correo.setText("")
            cantidad.setText("")
            catalogo.setSelection(0)
            carrito.clear()
            actualizarVistaCarrito()

            transporte.isChecked = false
            instalacion.isChecked = false
            garantia.isChecked = false

            descuento.progress = 0
            lblDescuento.text = "Descuento: 0%"
            calificacion.rating = 0f

            nombre.requestFocus()
        }

        volver.setOnClickListener {
            finish()
        }
    }
}