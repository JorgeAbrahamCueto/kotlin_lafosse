# 🪑 Lafosse Furniture

Aplicación móvil desarrollada en **Kotlin** para Android como proyecto de Evaluación Parcial.

La aplicación representa a la empresa **Lafosse Furniture**, especializada en la comercialización de mobiliario corporativo, educativo y empresarial.

---

# 📱 Características

- Splash Screen con barra de progreso de 5 segundos.
- Inicio de sesión.
- Navigation Drawer.
- Presentación de la empresa.
- Formulario de contacto.
- Catálogo de productos.
- Sistema de cotización.
- Cálculo de descuentos.
- Cálculo de servicios adicionales.
- Resultado detallado de la cotización.
- Cierre de sesión.
- Diseño bloqueado en orientación vertical.

---

# 🛠 Tecnologías utilizadas

- Kotlin
- Android Studio
- Android SDK
- Material Design
- Fragments
- Activities
- Navigation Drawer

---

# 📂 Arquitectura General

```text
SplashActivity
      ↓
LoginFragment
      ↓
HomeFragment
      ↓
Navigation Drawer

├── Inicio
├── Nosotros
├── Contactos
└── Cerrar Sesión

      ↓
CatalogoActivity
      ↓
ResultadoActivity
```

---

# ✅ Requisitos

- Android Studio Hedgehog o superior.
- JDK 17 o superior.
- Gradle.
- Emulador Android o dispositivo físico.

---

# ⚙️ Instalación

## 1. Clonar el repositorio

```bash
git clone https://github.com/JorgeAbrahamCueto/kotlin_lafosse.git
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

## 3. Sincronizar Gradle

Esperar que Android Studio descargue las dependencias necesarias.

## 4. Ejecutar

Pulsar:

```text
Run ▶
```

o

```text
Shift + F10
```

---

# 🔑 Credenciales de Acceso

Usuario:

```text
admin
```

Contraseña:

```text
123
```

---

# 🧪 Flujo de Prueba

## Login Incorrecto

Ingresar:

```text
Usuario: admin
Contraseña: 000
```

Resultado esperado:

```text
Usuario o contraseña incorrectos
```

---

## Login Correcto

Ingresar:

```text
Usuario: admin
Contraseña: 123
```

Resultado esperado:

```text
Bienvenido Administrador
```

---

## Navegación

Verificar acceso a:

- Inicio
- Nosotros
- Contactos
- Cerrar Sesión

---

## Contactos

Completar:

- Nombre
- Correo
- Teléfono
- Mensaje

Resultado esperado:

```text
Mensaje enviado
```

---

## Cotización

Seleccionar:

- Producto
- Cantidad
- Servicios adicionales
- Descuento

Resultado esperado:

- Precio unitario.
- Subtotal.
- Adicionales.
- Descuento.
- Total final.

---

# 📊 Fórmula de Cotización

```text
Subtotal = Precio × Cantidad
```

```text
Subtotal con Adicionales =
Subtotal + Transporte + Instalación + Garantía
```

```text
Monto de Descuento =
Subtotal con Adicionales × % Descuento
```

```text
Total Final =
Subtotal con Adicionales - Monto Descuento
```

---

# 📸 Capturas

Agregar las siguientes capturas:

- Splash Screen
- Login
- Inicio
- Nosotros
- Contactos
- Catálogo
- Resultado de Cotización

---

# 👨‍💻 Autor

Jorge Abraham Cueto Elias

Ricky Estrella Pacho

Proyecto desarrollado para el curso de Programación Móvil.
