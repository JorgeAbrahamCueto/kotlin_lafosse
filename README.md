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

├── Inicio        → HomeFragment
├── Nosotros      → NosotrosFragment
├── Ubícanos      → MapaFragment (Google Maps API)
├── Contáctanos   → ContactoActivity (Firestore: contactos)
└── Cerrar Sesión → LoginActivity

      ↓ (desde Inicio)
CatalogoActivity (Cotizador multiproduto + Firestore: cotizaciones)
      ↓
ResultadoActivity
```

---

# 🗄️ Colecciones en Cloud Firestore

- **`usuarios`**: Almacena los datos de los usuarios registrados y autenticados.
- **`cotizaciones`**: Guarda el cliente, correo, lista de productos cotizados, servicios adicionales, descuento, calificación, total y fecha.
- **`contactos`**: Registra las consultas enviadas desde el formulario de Contáctanos (nombre, correo, teléfono, tipo de cliente, mensaje y fecha).

---

# ✅ Requisitos

- Android Studio Hedgehog o superior.
- JDK 17 o superior.
- Gradle.
- Archivo `google-services.json` de Firebase en `app/`.
- API Key de Google Maps configurada en `AndroidManifest.xml`.
- Emulador Android o dispositivo físico con conexión a internet.

---

# ⚙️ Instalación

## 1. Clonar el repositorio

```bash
git clone [https://github.com/JorgeAbrahamCueto/kotlin_lafosse.git](https://github.com/JorgeAbrahamCueto/kotlin_lafosse.git)
```

## 2. Abrir el proyecto

Abrir Android Studio y seleccionar:

```text
Open Project
```

Seleccionar la carpeta:

```text
kotlin_lafosse
```

## 3. Configurar Firebase y Google Maps

- Verificar que `google-services.json` se encuentre dentro de `app/`.
- Verificar la clave `com.google.android.geo.API_KEY` en `app/src/main/AndroidManifest.xml`.

## 4. Sincronizar Gradle

Esperar que Android Studio descargue las dependencias necesarias.

## 5. Ejecutar

Pulsar:

```text
Run ▶
```

o

```text
Shift + F10
```

---

# 🔑 Acceso y Autenticación

El acceso está gestionado mediante **Firebase Authentication**:

- **Registrarse:** Crear una cuenta nueva desde la pantalla de registro con correo y contraseña.
- **Iniciar sesión:** Ingresar con una cuenta registrada o mediante el botón **Continuar con Google**.

---

# 🧪 Flujo de Prueba

## Registro e Inicio de Sesión

- Intentar ingresar con datos incorrectos: muestra alerta de error de autenticación.
- Registrarse o iniciar sesión con correo válido o Google: guarda el perfil en Firestore (`usuarios`) e ingresa a `MainActivity`.
- Cerrar y volver a abrir la app: tras el Splash de 5 segundos, ingresa directo a Inicio manteniendo la sesión activa.

---

## Navegación

Verificar acceso desde el Navigation Drawer a:

- Inicio
- Nosotros
- Ubícanos (carga del mapa y marcador del showroom)
- Contáctanos
- Cerrar Sesión (regresa a `LoginActivity`)

---

## Contactos

Completar:

- Nombre completo
- Correo electrónico (autocompletado con la sesión activa)
- Teléfono
- Tipo de cliente (Hogar / Empresa)
- Mensaje

Resultado esperado:

```text
¡Gracias [Nombre]! Tu mensaje fue enviado.
```

*(El registro queda guardado en la colección `contactos` de Firestore y el botón **Volver** regresa siempre a Inicio).*

---

## Cotización Multiproduto

Completar:

- Nombre y correo del cliente
- Seleccionar producto + cantidad y pulsar **+ Agregar** (repetir para varios muebles)
- Servicios adicionales (Transporte, Instalación, Garantía Extendida)
- Descuento (%) y calificación (estrellas)

Resultado esperado:

- Detalle de todos los productos agregados.
- Subtotal de muebles.
- Adicionales.
- Descuento aplicado.
- Calificación y Total Final.
- Registro guardado en la colección `cotizaciones` de Firestore.

---

# 📊 Fórmula de Cotización

```text
Subtotal = Σ (Precio Unitario × Cantidad de cada producto)
```

```text
Subtotal con Adicionales =
Subtotal + Transporte + Instalación + Garantía
```

```text
Monto de Descuento =
Subtotal con Adicionales × (% Descuento / 100)
```

```text
Total Final =
Subtotal con Adicionales - Monto Descuento
```

---

# 📸 Capturas

Agregar las siguientes capturas:

- Splash Screen
- Login y Registro
- Inicio
- Nosotros
- Ubícanos (Google Maps)
- Contáctanos
- Catálogo (Cotizador multiproduto)
- Resultado de Cotización

---

# 👨‍💻 Autores

Jorge Abraham Cueto Elias

Ricky Estrella Pacho

Proyecto desarrollado para el curso de Programación Móvil.
