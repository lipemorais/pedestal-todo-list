FROM clojure:lein-alpine AS dev
WORKDIR /dev
COPY . /dev
RUN lein uberjar

FROM openjdk:alpine AS prod
COPY --from dev todo-list-standalone.jar --to prod /work
WORKDIR /work
CMD java -jar todo-list-standalone.jar
