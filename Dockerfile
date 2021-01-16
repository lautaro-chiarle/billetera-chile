FROM openjdk:8-jdk-alpine
MAINTAINER lautaro-chairle
VOLUME /tmp
EXPOSE 8080
ADD target/desafio-0.0.1-SNAPSHOT.jar desafio.jar
ENTRYPOINT ["java","-jar","/desafio.jar"]