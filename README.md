# Literalura 📚

**Literalura** es una aplicación para gestionar libros y autores, ideal para bibliotecas, coleccionistas y amantes de la lectura. Permite realizar búsquedas de libros por título, gestionar estadísticas de descargas y consultar información detallada sobre autores.

Este proyecto está construido con Java, usando Spring y otras herramientas tecnológicas, y tiene como objetivo ofrecer una plataforma eficiente para almacenar, buscar y analizar libros y autores.

---

## 🚀 Funcionalidades

- **Búsqueda de libros por título**: Busca libros en una base de datos o mediante una API externa.
- **Listado de libros**: Visualiza todos los libros registrados.
- **Listado de autores**: Visualiza todos los autores registrados.
- **Consulta de autores vivos**: Verifica qué autores están vivos en un año específico.
- **Filtrado de libros por idioma**: Muestra libros disponibles en un idioma determinado.
- **Estadísticas de libros por idioma**: Visualiza un resumen de los libros disponibles por cada idioma.
- **Top 10 libros más descargados**: Muestra los 10 libros más descargados en la plataforma.
- **Estadísticas de descargas por autor**: Proporciona análisis de las descargas totales, promedio, máximo y mínimo de los libros de cada autor.

---

## 📦 Requisitos

- **Java 17** o superior.
- **Maven** para la gestión de dependencias.
- **Base de datos**: MySQL o H2 (configurable).
- **IDE recomendada**: IntelliJ IDEA, Eclipse, o Visual Studio Code.

---

## ⚙️ Instalación y Uso

### Clonar el repositorio

Clona este repositorio para comenzar a trabajar en el proyecto:

```bash
git clone https://github.com/tu_usuario/literalura.git 
```
# ⚙️ Configuración del entorno

## Requisitos previos

Asegúrate de tener **Java 17** o superior instalado. Puedes verificar tu versión con el siguiente comando:

```bash
java -version
```

Configura **Maven** para gestionar las dependencias. Si no tienes Maven instalado, puedes seguir las instrucciones en la [documentación oficial de Maven](https://maven.apache.org/install.html).

Si estás usando una base de datos como **MySQL**, asegúrate de configurarla correctamente en el archivo `application.properties`. Aquí tienes un ejemplo de cómo configurarlo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Ejecutar la aplicación

1. **Navega a la carpeta del proyecto**:

    ```bash
    cd literalura
    ```

2. **Compila y ejecuta el proyecto con Maven**:

    ```bash
    mvn spring-boot:run
    ```

---

## 📜 Ejemplo de código

A continuación se muestra un ejemplo de código básico para inicializar la aplicación:

```java
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";

    public static void main(String[] args) {
        System.out.println("Bienvenido a Literalura");
        // Inicialización y ejecución del programa
    }

    public void muestraElMenu() {
        // Lógica para mostrar el menú
    }
}
```

---

## 🧑‍🤝‍🧑 Contribuir

¡Contribuye al proyecto! Si encuentras errores o tienes ideas para nuevas funcionalidades, no dudes en abrir un *issue* o enviar un *pull request*.

1. Haz un **fork** del repositorio.
2. Crea una rama con tu cambio:

    ```bash
    git checkout -b nueva-funcionalidad
    ```

3. Realiza el cambio y haz **commit**:

    ```bash
    git commit -am 'Agrega nueva funcionalidad'
    ```

4. Empuja tu rama:

    ```bash
    git push origin nueva-funcionalidad
    ```

5. Abre un **pull request**.

## 📞 Contacto

- **Correo electrónico**: juanse951@gmail.com
- **LinkedIn**: [Tu LinkedIn](https://www.linkedin.com/in/juanse951/)

