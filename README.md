# Fiera Link Tracker Challenge
## Angel Perez

### Acerca
El siguiente proyecto es un desafió de la organización Fiera Studio.

* El sistema ha sido implementado en Java con el Framework Spring (data, security, boot) con la utilización de maven como manejador de dependencias.

* Se ha utilizado como base de datos H2

### Patrones/Arquitectura
Se ha utilizado la arquitectura MVC (Modelo Vista Controlador)

Se define un modelo de datos que en este caso incluye una clase/tabla/entidad la cual es "LinkStorage".

En la capa de vista se ha implementado de forma que responda a una API REST, 
por lo que toda respuesta del sistema viene en este formato de texto plano, 
con detalles del estado consultado a la API (status HTTP).

En la capa controlador, como ya se adelantó es una API REST que recibe parámetros en forma de query, de body, y de path.
Se puede dividir en el modelo de 3 capas: Controller, Service, Repository.
* Capa Controller, recibe las solicitudes del usuario desde la Vista.
* Capa Service, maneja la lógica de negocio.
* Capa Repository, es la interfase con la capa de Modelo.

#### Detalles extras de implementación
Se manejan todos los errores producidos por ingreso de parte del usuario con excepciones de Java.
Se utilizan mappers para disminuir el acoplamiento en la capa de Servicio.
Se utiliza el patron DTO para el paso de mensajes y datos entre la capa de Vista y Controlador.
##### Se utiliza Swagger 3 para la documentación de la API Rest

### Instrucciones para correr o levantar el entorno

Clonar Repositorio de GitHub

> git clone https://github.com/pasaperez/Fiera-Link-Tracker-AngelPerez

* 1 Abrir con un IDE y ejecutar el módulo desde el archivo main:

> FieraLinkTrackerAngelPerezApplication.java

* 1' O abrir con un IDE y correr el comando en la carpeta root dentro del proyecto: 

> mvn clean package

* 1'.1 - Si se corrió con el comando anterior se generó él .jar ejecutarlo con java:

> java -jar target/Fiera-Link-Tracker-AngelPerez-RELEASE-1.0.3.jar

* 2 - Acceder al siguiente link:

> http://localhost:10000/swagger-ui.html

* Se podrán observar los endpoints creados y probarlos
  * Haciendo click en POST -> /create -> "Try it Out"
  * Haciendo click en GET -> /stat/{subUrl} -> "Try it Out"
  * Haciendo click en PUT -> /invalidate/{subUrl} -> "Try it Out"

El 1.º endpoint devuelve el objeto solicitado y en él el link para acceder a una redirección. (El atributo link se puede abrir en otra pestaña/ventana del navegador)

El 2.º endpoint se puede acceder desde una nueva pestaña o ventana del navegador

El 3.º se puede ejecutar desde la misma página de swagger.

#### Tener en cuenta que:

Para 2° y 3° el parámetro {subUrl} es el postfijo que se crea en el endpoint /create

Para 1° en la última version del código (clone por default) se debe adjuntar al final del link: ´?password=´ <password ingresado en /create> 

### Se puede acceder a la base de datos

Ingresando al siguiente link:

> http://localhost:10000/h2-console

Cadena: jdbc:h2:mem:testdb
Usuario: sa
Password:
(Vació)

### Diagrama básico de clases/datos
![Diagrama](https://raw.githubusercontent.com/pasaperez/Fiera-Link-Tracker-AngelPerez/main/src/main/resources/Modelo-Datos-Unico.png)

#### Pregunta extra
¿Cómo se optimizaría la implementation y la base de datos para publicar en producción, para asegurar una alta demanda simultánea de requests.?

Este sistema se puede implementar con spring cloud o con sistemas alternativos, en los cuales se replica multiples veces el sistema lógico o se dividen los endpoints. 
Al usar un balanceador de carga se puede asegurar una disponibilidad escalable.
La base de datos también se puede implementar similarmente, en cuanto a consultas se puede replicar en distintos nodos la BD (siempre teniendo en cuenta la integridad de los datos).