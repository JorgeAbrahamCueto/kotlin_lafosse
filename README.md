# 🪑 Lafosse Furniture

Aplicación móvil desarrollada en **Kotlin** para Android como proyecto de **Evaluación Final**.

La aplicación representa a la empresa **Lafosse Furniture**, especializada en la comercialización de mobiliario corporativo, educativo y empresarial, integrando servicios en la nube con **Firebase** y geolocalización con **Google Maps API**.

---

# 📱 Características

- Splash Screen animado con barra de progreso de 5 segundos y verificación automática de sesión.
- Inicio de sesión con **Correo/Contraseña** y **Google Sign-In** (Firebase Authentication).
- Registro de nuevos usuarios con almacenamiento de perfil en **Cloud Firestore**.
- Sesión persistente (mantiene la sesión iniciada al cerrar y reabrir la aplicación).
- Navigation Drawer con Fragments y Activities.
- Presentación de la empresa (Inicio y Nosotros) con tipografía estandarizada (Título `28sp`, Párrafo `24sp`, Interlineado `30sp`).
- Mapa interactivo de ubicación de la empresa con **Google Maps API**.
- Formulario de contacto conectado a **Cloud Firestore** (`contactos`) con retorno directo al Inicio.
- Catálogo de productos con **cotizador multiproduto** (permite agregar varios muebles en una sola cotización).
- Cálculo de servicios adicionales, descuentos (`SeekBar`) y calificación (`RatingBar`).
- Resultado detallado de la cotización y guardado automático en **Cloud Firestore** (`cotizaciones`).
- Botón de cierre de sesión en el Navigation Drawer (cierra Firebase y Google limpiando el historial).
- Ícono de aplicación personalizado y diseño bloqueado en orientación vertical (`portrait`).

---

# 🛠 Tecnologías utilizadas

- Kotlin
- Android Studio
- Android SDK
- Material Design
- Fragments y Activities
- Navigation Drawer
- Firebase Authentication (Correo/Contraseña y Google Sign-In)
- Cloud Firestore
- Google Maps SDK for Android

---

# 📂 Arquitectura General

```text
SplashActivity (5s + verificación de sesión)
      ↓
LoginActivity  ⟷  RegistroActivity
      ↓
MainActivity (Navigation Drawer)

├── Inicio       → HomeFragment
├── Nosotros     → NosotrosFragment
├── Ubícanos     → MapaFragment (Google Maps API)
├── Contáctanos  → ContactoActivity (Firestore: contactos)
└── Cerrar Sesión → LoginActivity

      ↓ (desde Inicio)
CatalogoActivity (Cotizador multiproduto + Firestore: cotizaciones)
      ↓
ResultadoActivity
