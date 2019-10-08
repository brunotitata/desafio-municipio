FROM openjdk:8-jre-alpine
MAINTAINER br.com.senior
RUN mkdir /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
ENTRYPOINT exec java -jar demo-0.0.1-SNAPSHOT.jar