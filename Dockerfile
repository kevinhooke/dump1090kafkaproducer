FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2/repository mvn -B dependency:go-offline
COPY src ./src
RUN --mount=type=cache,target=/root/.m2/repository mvn -B package -DskipTests

FROM eclipse-temurin:17-jre
RUN apt update && apt upgrade -y && apt install -y netcat-openbsd

COPY --from=builder /build/target/dump1090-kafkaproducer-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
ENV PIAWARE_HOST=piaware.local
ENV RESTCLIENT_URIBASE=http://10.0.10.2:8001
ENTRYPOINT ["sh", "-c", "while true; do nc -w 60 ${PIAWARE_HOST} 30003 | java -jar /app.jar; sleep 5; done"]
