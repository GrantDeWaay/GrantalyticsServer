FROM openjdk:21-jdk
WORKDIR /app
COPY ./build/libs/GrantalyticsServer-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "GrantalyticsServer-0.0.1-SNAPSHOT.jar"]