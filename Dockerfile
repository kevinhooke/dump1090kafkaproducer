FROM eclipse-temurin:17
ARG JAR_FILE=target/dump1090-kafkaproducer-0.0.1-SNAPSHOT-jar-with-dependencies.jar
ENV PIAWARE_HOST=piaware.local
RUN apt update && apt upgrade -y && apt install -y netcat-openbsd

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "nc ${PIAWARE_HOST} 30003 | java -jar /app.jar"]