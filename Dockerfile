FROM openjdk:8-jdk-alpine3.9
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eolt.jar
EXPOSE 8089
RUN echo "Europe/Warsaw" > /etc/timezone

ENTRYPOINT ["java","-jar","/eolt.jar"]