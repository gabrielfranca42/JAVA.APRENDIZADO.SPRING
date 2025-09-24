FROM eclipse-temurin:17
LABEL maintainer="gabrielfranca42"
WORKDIR /aplicacao
COPY target/pratica.aprendiazdo.spring-0.0.1-SNAPSHOT.jar /aplicacao/docker-aplicacao
ENTRYPOINT["java", "-jar", "docker-aplicacao.jar"]