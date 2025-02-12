# Build stage
FROM gradle:8.8-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle build -x test

# Run stage
FROM --platform=linux/amd64 openjdk:21-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "app.jar"]
