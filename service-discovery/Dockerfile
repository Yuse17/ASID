FROM openjdk:17-jdk

COPY target/discovery-service.jar .

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "discovery-service.jar"]