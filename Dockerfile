FROM maven:3-amazoncorretto-8 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN --mount=type=cache,target=/root/.m2 mvn clean install assembly:single


FROM amazoncorretto:8

WORKDIR /app

COPY --from=build /app/target/WBD_SOAP_Service-1.0-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
