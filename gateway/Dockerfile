FROM openjdk:17-jdk

COPY target/gateway-service.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "gateway-service.jar"]