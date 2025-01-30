FROM eclipse-temurin:17

# LABEL maintainer="sam.ashray1@gmail.com"

# WORKDIR /app

# COPY src/main/resources/static/bible-project-0.0.1-SNAPSHOT.jar /app/bible-project.jar
# COPY src/main/resources/static/en_kjv.json /app/en_kjv.json

# # ENV MY_APP=xAsqw12

# EXPOSE 8080

# ENTRYPOINT ["java", "-jar", "bible-project.jar"]

# FROM adoptopenjdk/openjdk17
      
EXPOSE 8080
 
ENV APP_HOME /usr/src/app

COPY target/*.jar $APP_HOME/app.jar

WORKDIR $APP_HOME

CMD ["java", "-jar", "app.jar"]
