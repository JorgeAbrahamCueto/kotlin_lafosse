package com.example.examen_parcial

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawerLayout)
        val navView = findViewById<NavigationView>(R.id.navView)

        // Para que aparezca el nombre del usuario en el nav_header
        val headerView = navView.getHeaderView(0)
        val txtUsuario = headerView.findViewById<TextView>(R.id.txtUsuario)
        val usuarioActual = auth.currentUser
        val uid = usuarioActual?.uid

        // Cargar nombre del usuario desde Firestore
        if (uid != null) {
            db.collection("usuarios")
                .document(uid)
                .get()
                .addOnSuccessListener { documento ->
                    val nombre = documento.getString("nombre")
                    txtUsuario.text = if (!nombre.isNullOrBlank()) {
                        nombre
                    } else {
                        usuarioActual.displayName ?: usuarioActual.email ?: "Usuario"
                    }
                }
                .addOnFailureListener {
                    txtUsuario.text = usuarioActual.displayName ?: usuarioActual.email ?: "Usuario"
                }
        }

        // Configurar el ActionBarDrawerToggle para abrir y cerrar el menú
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.app_name,
            R.string.app_name
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        // Cargar HomeFragment por defecto al iniciar
        if (savedInstanceState == null) {
            replaceFragment(HomeFragment())
            navView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                replaceFragment(HomeFragment())
            }

            R.id.nav_nosotros -> {
                replaceFragment(NosotrosFragment())
            }

            R.id.nav_contacto -> {
                val ventana = Intent(this, ContactoActivity::class.java)
                startActivity(ventana)
            }

            R.id.nav_mapa -> {
                replaceFragment(MapaFragment())
            }

            R.id.nav_login -> {
                cerrarSesion()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun cerrarSesion() {
        // 1. Cerrar sesión en Firebase
        auth.signOut()

        // 2. Cerrar sesión en Google (si ingresó con Google)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()

        Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()

        // 3. Regresar al LoginActivity limpiando el historial de pantallas
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}