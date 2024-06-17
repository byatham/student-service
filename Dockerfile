# Use the official OpenJDK 17 image as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /student/app

# Copy the executable JAR file to the container
COPY  target/student-service.jar  /student/app/student-service.jar

# Make port 9091 available to the world outside this container
EXPOSE 9091

# Run the JAR file
ENTRYPOINT ["java","-jar","student-service.jar"]