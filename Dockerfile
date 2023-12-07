# Stage 1: Build stage
FROM openjdk:17 as builder

WORKDIR /app

RUN microdnf install findutils

COPY ./project .

RUN cd /app && ./gradlew build

FROM openjdk:17.0-oracle

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar .

CMD ["java", "-jar", "/app/kotlin-starter-1.0.jar"]