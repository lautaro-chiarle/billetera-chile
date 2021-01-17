# billetera-chile
#### Lautaro Chiarle - chiarlelautaro@gmail.com

![E-Wallet](https://img.etimg.com/thumb/width-640,height-480,imgsize-44351,resizemode-1,msid-56152604/wealth/spend/how-to-manage-e-wallets/e-wallet-bccl.jpg)

## Requerimientos


1. Debes desarrollar una API REST en Spring Boot con las siguientes funcionalidades:
    - a. Sign up usuarios.
    - b. Login usuarios.
    - c. Sumar dos números. Este endpoint debe retornar el resultado de la suma y puede ser  consumido solo por usuarios logueados.
    - d. Historial de todos los llamados al endpoint de suma. Responder en Json, con data paginada y el límite se encuentre en properties.
    - e. Logout usuarios.
    - f. El historial y la información de los usuarios se debe almacenar en una database PostgreSQL. 
    - g. Incluir errores http. Mensajes y descripciones para la serie 400.

2. Esta API debe ser desplegada en un docker container. Este docker puede estar en un dockerhub público. La base de datos también debe correr en un contenedor docker.

3. Debes agregar un Postman Collection o Swagger para que probemos tu API.

4. Tu código debe estar disponible en un repositorio público, junto con las instrucciones de cómo desplegar el servicio y cómo utilizarlo.


## Instrucciones para preparar el ambiente

### Clonar el Repo Git

```$ git clone https://github.com/lautaro-chiarle/billetera-chile.git```

### Posicionarse en la carpeta
```$ cd billetera-chile```

### Ejecutar Docker Compose
```$ docker-compose up --build```



## Instrucciones de Uso

- Una vez levantada la aplicación ingresar a http://localhost:8080/swagger-ui.html

- Acceder al endpoint *"/signin"* con uno de los siguientes usuarios

   ##### **User: juan@mail.com - Password: ju4n123!**
   ##### **User: pedro@mail.com - Password: p3dr0123!**

- Una vez ejecutado el Endpoint, copiar el token devuelto e introducirlo en el formulario de Autorización

![Authorize](https://mattfrear.files.wordpress.com/2018/07/authbutton.jpg)

- A partir de allí se puede ejecutar el resto de Endpoints que están securizados
