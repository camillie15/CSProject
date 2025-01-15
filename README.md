# VoxPopulity

VoxPopulity es un sistema de código abierto diseñado para la gestión de publicaciones y comentarios. Este proyecto está desarrollado con tecnologías modernas y escalables.

## 🚀 Tecnologías Utilizadas
- **Spring Boot** - Backend robusto y flexible
- **Thymeleaf** - Motor de plantillas para la generación de vistas dinámicas
- **MySQL** - Sistema de gestión de bases de datos relacional

Este servicio se ejecuta sobre **Apache Tomcat**.

---

## 🗄️ Configuración de la Base de Datos
Para poner en marcha el sistema, es necesario configurar la base de datos MySQL siguiendo los pasos a continuación:

1. **Importar el script de base de datos:**
   
   Ejecuta el script ubicado en `./src/main/resources/ScriptDB.sql` para crear las tablas necesarias:
   - `Users`
   - `Posts`
   - `Comments`

2. **Configuración del usuario de la base de datos:**
   - **Usuario:** `user_admin` (con rol `DBA_ROLE`)
   - **Contraseña:** `12345`
   - **Host:** `localhost`
   - **Puerto:** `3306`
   -**Nombre de la BD:** `voxpopulidb`

---

## ⚙️ Configuración del Proyecto

1. Clona el repositorio:
   ```bash
   https://github.com/camillie15/CSProject.git
   ```

2. Accede al directorio del proyecto:
   ```bash
   cd VoxPopulity
   ```

3. Configura el archivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/voxpopulidb
   spring.datasource.username=user_admin
   spring.datasource.password=12345
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   ```

4. Ejecuta el proyecto:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 📄 Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

## 🤝 Contribuciones
¡Las contribuciones son bienvenidas! Abre un issue o envía un pull request para mejorar el proyecto.

---

**VoxPopulity** - La voz de la comunidad al alcance de todos. 🌐

