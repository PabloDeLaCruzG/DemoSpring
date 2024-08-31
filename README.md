Actividad Spring - Acceso a Datos


Introducción 

He creado un nuevo proyecto Spring con las dependencias de Spring Data JPA, Spring Boot Devtools y Lombok. Para empezar, he creado una base de datos SQL en MySQLWorkbench, luego en el proyecto Spring he creado una estructura de paquetes para la creación y conexión de la ApiRest, una vez hecha la estructura, he hecho un controlador con los métodos de la Api GET, POST, PUT y REMOVE para controlar los datos, luego he creado otro controlador que obtiene una lista con una condición, por último, he creado un interceptor para definir un rango de horas de acceso a la web. Luego he hecho otro proyecto nuevo con React y la librería Axios para la conexión de Api, este proyecto ‘FrontEnd’ se conecta al otro proyecto ‘BackEnd’ a través de la Api. En el FrontEnd he creado una vista principal con el listado, un link a otra vista que contiene la lista con la condición, y botones para post, put y remove. 

Base de datos 

La base de datos la he creado en MySQLWorkBench, simplemente cree la base de datos Tienda con ‘CREATE DATABASE Tienda’ ya que las tablas y campos nos lo crea JPA directamente en la base de datos una vez se ejecuta la aplicación. 

Modelos 

En este caso solo hay un modelo en el proyecto, en los modelos es donde asignamos los campos e implementamos los métodos del modelo. Con la dependencia Lombok nos ahorramos escribir nosotros los métodos get y set de cada campo y los constructores, de esa manera también el código queda más limpio. Luego le ponemos las etiquetas de JPA para la generación de tablas y columnas en la base de datos. 

Repositorios 

El repositorio es la interfaz que contiene los métodos entre otros para luego implementarlos al ‘Service’. Para no tener que hacer los métodos, existe JpaRepository que es una interfaz en Spring Data JPA que proporciona métodos predefinidos para realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) en una base de datos utilizando JPA (Java Persistence API). Simplifica el acceso y la manipulación de datos al proporcionar métodos como save(), findById(), findAll(), delete(), entre otros, que se pueden utilizar directamente sin necesidad de escribir consultas SQL. Facilita el desarrollo de aplicaciones al reducir la cantidad de código necesario para interactuar con la base de datos, permitiendo a los desarrolladores enfocarse en la lógica de negocio. Podríamos nosotros crear el método que queramos, en mi caso con los que tiene me servían. 

Servicios 

En el servicio inyectaremos los métodos del Repositorio y crearemos los métodos CRUD que implementaremos en los controladores. 

 

Controladores y DTO 

ProductoDTO 

El DTO es el encargado de recibir el JSON y convertirlo para poder usarlo en los controladores. Usamos ‘record’ específicamente para representar datos de una manera simple y eficiente. 

 

ProductoController 

Los Controladores son los que manejan las solicitudes HTTP y determina las respuestas de GET, POST, PUT y DELETE. Este controlador es el de la vista principal. 

ProductoDescuento (Controller) 

Este controlador solo tiene el método GET que nos va a sacar un listado de los productos con la condición. 


Interceptor y WebConfig 

WebConfig 

He creado un archivo para las configuraciones del proyecto, en él se manejan los permisos de la Api y para registrar el interceptor. 

Interceptor 

El interceptor llama a la interfaz ‘HandlerInterceptor’ que esta proporciona métodos de callback que se invocan antes y después de manejar una solicitud HTTP. El método ‘preHandle’ se ejecuta antes de manejar la solicitud HTTP. En este caso, comprueba si la hora actual está dentro del horario de mantenimiento especificado. 


Conclusión del BackEnd 


En resumen, cuando iniciamos este proyecto Spring primero comprobará el interceptor y los permisos, si la hora esta fuera del rango se ejecutará la Api, entonces a través de un programa como ‘Postman’ podremos hacer solicitudes GET, POST, PUT y DELETE al primer controlador y al controlador de descuentos solo peticiones GET. 

Video del funcionamiento 
https://www.youtube.com/watch?v=Wd1Skiyh8KY&ab_channel=PabloDeLaCruz
