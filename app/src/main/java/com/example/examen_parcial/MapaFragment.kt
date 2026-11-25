package com.example.examen_parcial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Coordenadas de la tienda en Lima (puedes cambiarlas por las que desees)
        val ubicacionEmpresa = LatLng(-12.0906, -77.0229)

        googleMap.addMarker(
            MarkerOptions()
                .position(ubicacionEmpresa)
                .title("Lafosse Furniture")
                .snippet("Showroom Principal")
        )

        // Acercar la cámara con zoom nivel 16 y habilitar controles de zoom
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacionEmpresa, 16f))
        googleMap.uiSettings.isZoomControlsEnabled = true
    }
}