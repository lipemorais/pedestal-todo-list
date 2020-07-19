FROM openjdk:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/pedestal-todo-list-0.0.1-SNAPSHOT-standalone.jar /pedestal-todo-list/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/pedestal-todo-list/app.jar"]
