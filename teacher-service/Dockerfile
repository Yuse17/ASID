FROM openjdk:17-jdk

COPY target/teacher-service.jar .

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "teacher-service.jar"]