# billetera-chile
-


http://localhost:8080/swagger-ui.html


Usuarios:
 pedro@mail.com - p3dr0123!
 juan@mail.com - ju4n123!

1. Debes desarrollar una API REST en Spring Boot con las siguientes funcionalidades:
    a. Sign up usuarios.
    b. Login usuarios.
    c. Sumar dos números. Este endpoint debe retornar el resultado de la suma y puede ser consumido solo por usuarios logueados.
    d. Historial de todos los llamados al endpoint de suma. Responder en Json, con data paginada y el límite se encuentre en properties.
    e. Logout usuarios.
    f. El historial y la información de los usuarios se debe almacenar en una database PostgreSQL. 
    g. Incluir errores http. Mensajes y descripciones para la serie 400.

2. Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker.

3. Debes agregar un Postman Collection o Swagger para que probemos tu API.

4. Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.