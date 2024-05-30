FROM openjdk:17-jdk

COPY target/saga-service.jar .

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "saga-service.jar"]