FROM openjdk:17-jdk-alpine
WORKDIR /usr/share/app

COPY build/libs/application.jar app.jar

CMD ["java", "-jar", "app.jar"]