#  Tarea 3: Backend de la Práctica 2 (Android + API REST)

**Autor:** Kevin Zarco Sosa  
**Institución:** Escuela Superior de Cómputo (ESCOM - IPN)  


##  Stack Tecnológico

| Entorno | Tecnologías Utilizadas |
| :--- | :--- |
| **Backend** | Python 3.9, Flask, SQLAlchemy, Bcrypt, SQLite |
| **Infraestructura** | Docker, Docker Compose |
| **Frontend (Móvil)** | Android Studio (Kotlin/Java), XML, ViewBinding |
| **Cliente HTTP** | Retrofit 2, Gson |

---

##  Guía de Despliegue del Backend

El servidor expone el puerto `5000` y cuenta con tres endpoints principales (`GET /`, `POST /register`, `POST /login`). Para iniciarlo, sigue estos pasos:

1. Asegúrate de tener el motor de **Docker Desktop** ejecutándose en tu sistema.
2. Abre una terminal y sitúate en la raíz del directorio del backend (donde está el archivo `docker-compose.yml`).
3. Ejecuta el orquestador de contenedores con el siguiente comando:
   ```bash
   docker compose up --build
 4-.El servidor estará activo y escuchando peticiones en http://localhost:5000.
 
---

##  Compilación de la Aplicación Android

Para probar la aplicación cliente, es necesario configurar correctamente la red dependiendo del entorno de pruebas:

1. Abre el proyecto Android usando **Android Studio**.
2. Espera a que Gradle sincronice todas las dependencias.
3. Verifica la URL base en la configuración de Retrofit:
   * *Para el Emulador de Android Studio:* Usa la dirección `http://10.0.2.2:5000/`.
   * *Para un celular físico:* Cambia la dirección por la IP de tu red local (ejemplo: `http://192.168.1.75:5000/`).
4. Haz clic en **Run 'app'** (Shift + F10) para compilar e instalar la aplicación.

---

##  Ejercicios (Capturas de Pantalla)

A continuación, se documenta el correcto funcionamiento de los 4 ejercicios solicitados en la práctica:

###  Ejercicio 1: Estado del Servidor
Demostración de la conexión exitosa al iniciar la app. Petición GET al endpoint `/`.

<details>
  <summary>📸 Click para ver captura</summary>
  <img src="Recursos/1.png" alt="Estado del Servidor">
</details>

###  Ejercicio 2: Flujo de Registro de Usuarios
Validación de la creación de un usuario en la base de datos y manejo de restricciones. Petición POST a `/register`.

<details>
  <summary>📸 Click para ver Registro Exitoso</summary>
  <img src="Recursos/3.png" alt="Registro Exitoso">
</details>

<details>
  <summary>📸 Click para ver Error Usuario Duplicado</summary>
  <img src="Recursos/3.5.png" alt="Usuario Duplicado">
</details>

###  Ejercicio 3: Autenticación (Login)
Comprobación de credenciales y redirección de vistas. Petición POST a `/login`.

<details>
  <summary>📸 Click para ver Login Exitoso</summary>
  <img src="Recursos/2.png" alt="Login Exitoso">
</details>

###  Ejercicio 4: Tolerancia a Fallos de Red
Manejo de excepciones en Retrofit. Se simuló la caída del servidor deteniendo el contenedor de Docker.

<details>
  <summary>📸 Click para ver Error de Red</summary>
  <img src="Recursos/4.jpeg" alt="Error de red">
</details>
