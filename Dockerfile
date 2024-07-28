FROM openjdk:17-jdk-slim-buster

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=kube","-jar","/app.jar"]