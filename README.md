# Catálogo Digital - Kotlin + Firebase

Este proyecto es una aplicación móvil construida con **Android Studio**, **Kotlin** y **Jetpack Compose** que funciona como un catálogo digital (vitrina de productos). Permite a los usuarios autenticarse (iniciar sesión) y visualizar un listado de productos con imagen, precio, nombre y una descripción corta expandible. Los productos se almacenan localmente, y los usuarios se autentican mediante Firebase.

---

## ✨ Características

- Pantalla de inicio de sesión con validación.
- Autenticación de usuarios usando Firebase Firestore.
- Catálogo de productos con imágenes, precios y descripciones.
- Descripciones expandibles/colapsables.
- Navegación segura entre pantallas.
- Arquitectura modular y escalable.
- Soporte para logout y manejo básico de estado.
## 🚀 Cómo Ejecutar el Proyecto

### 1. Clona este repositorio

```bash
git clone https://github.com/tuusuario/catalogo-digital-app.git
cd catalogo-digital-app
```
### 2. Abre el proyecto en Android Studio
Asegúrate de tener Android Studio Hedgehog o superior.

Abre la carpeta como proyecto.

### 3. Configura Firebase
Este proyecto usa **Firebase Firestore** para autenticar usuarios. Para ponerlo a funcionar, sigue estos pasos:

### a. Crea un proyecto en [Firebase Console](https://console.firebase.google.com/)

### b. Agrega una nueva app Android en Firebase

- Copia el **ID del paquete** de tu proyecto.
- Descarga el archivo `google-services.json`.
- Coloca el archivo en la carpeta `app/`.

### c. Habilita Firestore

- En Firebase Console, ve a **Firestore Database**.
- Crea una base de datos en modo de prueba (puedes ajustar las reglas luego).

### d. Crea manualmente una colección llamada `user`

- En Firestore, crea una **colección** con el nombre `user`.
- Añade documentos donde el **ID del documento sea el correo electrónico del usuario**.
- Cada documento debe tener al menos el siguiente campo:


```json
{
  "password": "123456"
}
```

### 4. Verifica los valores sensibles
Asegúrate de que no se suban a Git archivos como google-services.json ni los valores en res/values/values.xml que contienen claves de conexión.

Agrega al .gitignore si no lo tienes:

```
google-services.json
app/src/main/res/values/values.xml
```
Y en values.xml puedes dejar los valores vacíos o usar un archivo de entorno local.

### ✅ Requisitos Previos
* Android Studio instalado.
* Cuenta de Firebase.
* Dispositivo o emulador Android.
* Conexión a Internet.

### 🧪 Notas adicionales
- Por ahora, los productos están almacenados de forma local como una lista estática.
- El proyecto está diseñado para ser fácilmente escalable e incluir un backend remoto en el futuro.
  
### 🙌 Contribuciones
¿Quieres contribuir? Crea un fork, haz tus cambios y envía un pull request.
