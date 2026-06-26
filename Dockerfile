FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre
RUN apt update && apt upgrade -y && apt install -y netcat-openbsd

COPY --from=builder /build/target/dump1090-kafkaproducer-0.0.1-SNAPSHOT-jar-with-dependencies.jar app.jar
ENV PIAWARE_HOST=piaware.local
ENTRYPOINT ["sh", "-c", "nc ${PIAWARE_HOST} 30003 | java -jar /app.jar"]
