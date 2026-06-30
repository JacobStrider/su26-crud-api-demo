# Stage 1: Build the application using JDK 25
# -select a lightweight jdk image for building the application
FROM eclipse-temurin:25-jdk-alpine AS build

# Set the working directory inside the container
WORKDIR /app

#Copy everything from the current directory to the working directory in the container
COPY . .

# Add executable permissions to the maven wrapper script
RUN chmod +x mvnw

# Build the application using Maven Wrapper
RUN ./mvnw clean package -DskipTests

# Stage 2: Create a lightweight runtime image using JRE 25
FROM eclipse-temurin:25-jre-alpine

# Set the working directory inside the container
WORKDIR /app

#Copy the built application from the build stage to the runtime image
COPY --from=build /app/target/*.jar CrudApiApplication.jar

#Expose the port on which the application will run in the container
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "CrudApiApplication.jar"]
