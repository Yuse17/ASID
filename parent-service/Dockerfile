FROM openjdk:17-jdk

COPY target/parent-service.jar .

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "parent-service.jar"]