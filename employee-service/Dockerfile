FROM openjdk:17-jdk

COPY target/employee-service.jar .

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "employee-service.jar"]