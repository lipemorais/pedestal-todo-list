FROM clojure:lein-alpine AS dev
WORKDIR /build
COPY . .
RUN ["lein", "uberjar"]

FROM openjdk:alpine AS prod
WORKDIR /work
COPY --from=dev /build/target/todo-list-standalone.jar ./
CMD java -jar todo-list-standalone.jar

