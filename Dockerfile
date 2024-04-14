FROM eclipse-temurin:17

LABEL maintainer="sam.ashray1@gmail.com"

WORKDIR /app

COPY target/bible-project-0.0.1-SNAPSHOT.jar /app/bible-project.jar
COPY src/main/resources/static/en_kjv.json /app/en_kjv.json

ENTRYPOINT ["java", "-jar", "bible-project.jar"]