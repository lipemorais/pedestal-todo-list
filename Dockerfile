FROM openjdk:8-alpine
MAINTAINER Felipe de Morais <felipejpa15@gmail.com>

ADD target/pedestal-todo-list-0.0.1-SNAPSHOT-standalone.jar /pedestal-todo-list/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/pedestal-todo-list/app.jar"]
