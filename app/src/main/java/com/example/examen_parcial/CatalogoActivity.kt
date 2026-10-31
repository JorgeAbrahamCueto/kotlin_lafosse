package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CatalogoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_catalogo)

        val nombre = findViewById<EditText>(R.id.txtNombre)
        val correo = findViewById<EditText>(R.id.txtCorreo)
        val catalogo = findViewById<Spinner>(R.id.spnCatalogo)
        val cantidad = findViewById<EditText>(R.id.txtCantidad)

        val transporte = findViewById<CheckBox>(R.id.chkTransporte)
        val instalacion = findViewById<CheckBox>(R.id.chkInstalacion)
        val garantia = findViewById<CheckBox>(R.id.chkGarantia)

        val descuento = findViewById<SeekBar>(R.id.skDescuento)
        val lblDescuento = findViewById<TextView>(R.id.lblDescuento)

        val cotizar = findViewById<Button>(R.id.btnCotizar)
        val limpiar = findViewById<Button>(R.id.btnLimpiar)
        val volver = findViewById<Button>(R.id.btnVolver)

        // Mostrar porcentaje del SeekBar
        descuento.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    lblDescuento.text = "Descuento: $progress%"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            }
        )

        cotizar.setOnClickListener {

            if (nombre.text.toString().isEmpty() ||
                correo.text.toString().isEmpty() ||
                cantidad.text.toString().isEmpty()
            ) {
                lblDescuento.text = "Descuento: ${descuento.progress}%"

                android.widget.Toast.makeText(
                    this,
                    "Debe llenar todos los campos",
                    android.widget.Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            var precio = 0.0

            when (catalogo.selectedItem.toString()) {

                "Silla Ejecutiva ---- 350" -> precio = 350.0

                "Silla Gerencial ---- 1200" -> precio = 1200.0

                "Escritorio Ejecutivo ---- 1800" -> precio = 1800.0

                "Mesa de Reuniones ---- 450" -> precio = 450.0

                "Módulo de Recepción ---- 600" -> precio = 600.0

                "Butaca para Auditorio ---- 250" -> precio = 250.0

                "Archivador Metálico ---- 1500" -> precio = 1500.0
            }

            var total = precio * cantidad.text.toString().toDouble()
            val subtotal = precio * cantidad.text.toString().toDouble()


            var adicionales = ""
            var montoAdicional = 0.0

            if (transporte.isChecked) {
                adicionales += "Transporte (+ S/. 50)\n"
                montoAdicional += 50.0
            }

            if (instalacion.isChecked) {
                adicionales += "Instalación (+ S/. 100)\n"
                montoAdicional += 100.0
            }

            if (garantia.isChecked) {
                adicionales += "Garantía Extendida (+ S/. 200)\n"
                montoAdicional += 200.0
            }
            val subtotal2 = subtotal + montoAdicional

            val porcentajeDescuento = descuento.progress
            val montoDescuento =
                subtotal2 * porcentajeDescuento / 100.0

            total = subtotal2 - montoDescuento

            val intent = Intent(this, ResultadoActivity::class.java)

            intent.putExtra("nombre", nombre.text.toString())
            intent.putExtra("producto", catalogo.selectedItem.toString())
            intent.putExtra("cantidad", cantidad.text.toString())
            intent.putExtra("precio", precio)
            intent.putExtra("subtotal", subtotal)
            intent.putExtra("adicionales", adicionales)
            intent.putExtra("montoAdicional", montoAdicional)
            intent.putExtra("subtotal2", subtotal2)
            intent.putExtra("descuento", porcentajeDescuento)
            intent.putExtra("montoDescuento", montoDescuento)
            intent.putExtra("total", total)

            startActivity(intent)
        }

        limpiar.setOnClickListener {

            nombre.setText("")
            correo.setText("")
            cantidad.setText("")

            catalogo.setSelection(0)

            transporte.isChecked = false
            instalacion.isChecked = false
            garantia.isChecked = false

            descuento.progress = 0
            lblDescuento.text = "Descuento: 0%"

            nombre.requestFocus()
        }

        volver.setOnClickListener {
            finish()
        }
    }
}