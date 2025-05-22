FROM openjdk:17-jdk-slim
LABEL authors="noj23"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ms-authorization.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/ms-authorization.jar"]