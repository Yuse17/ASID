FROM openjdk:17-jdk

COPY target/town-service.jar .

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "town-service.jar"]