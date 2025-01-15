FROM amazoncorretto:21-alpine-jdk
RUN apk --no-cache add bash curl

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]