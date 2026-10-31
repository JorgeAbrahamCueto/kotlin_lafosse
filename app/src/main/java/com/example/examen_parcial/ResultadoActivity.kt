package com.example.examen_parcial

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)
        val resultado = findViewById<TextView>(R.id.txtResultados)
        val nombre = intent.getStringExtra("nombre")
        val producto = intent.getStringExtra("producto")
        val cantidad = intent.getStringExtra("cantidad")
        val precio = intent.getDoubleExtra("precio", 0.0)
        val subtotal = intent.getDoubleExtra("subtotal", 0.0)
        val adicionales = intent.getStringExtra("adicionales")
        val montoAdicional = intent.getDoubleExtra("montoAdicional", 0.0)
        val subtotal2 = intent.getDoubleExtra("subtotal2", 0.0)
        val descuento = intent.getIntExtra("descuento", 0)
        val montoDescuento = intent.getDoubleExtra("montoDescuento", 0.0)
        val total = intent.getDoubleExtra("total", 0.0)

        resultado.text =
            "Cliente: $nombre\n\n" +
                    "Producto seleccionado: $producto\n\n" +
                    "Cantidad: $cantidad\n\n" +
                    "Precio unitario: S/. $precio\n\n" +
                    "Precio total sin descuento: S/. $subtotal\n\n" +
                    "Servicios adicionales:\n$adicionales\n" +
                    "Monto de adicionales: S/. $montoAdicional\n\n" +
                    "Subtotal con adicionales: S/. $subtotal2\n\n" +
                    "Descuento aplicado: $descuento%\n\n" +
                    "Monto descontado: S/. $montoDescuento\n\n" +
                    "Total a pagar: S/. $total"




        val volver = findViewById<Button>(R.id.btnVolver)
        volver.setOnClickListener {
            finish()
        }
    }
    }
