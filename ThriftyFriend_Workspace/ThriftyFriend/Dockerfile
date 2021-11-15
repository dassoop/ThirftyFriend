FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/ThriftyFriend-0.0.1-SNAPSHOT.war app.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.war"]
