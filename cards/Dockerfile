# Use the official OpenJDK image as the base
FROM openjdk:17-jdk-slim
MAINTAINER rajanesh.kumar

# Install curl
RUN apt-get update && apt-get install -y curl && apt-get clean

# Create the necessary directory for the OpenTelemetry agent
RUN mkdir -p /app/libs

# Copy the OpenTelemetry Java agent JAR file
COPY opentelemetry-javaagent-1.27.0.jar /app/libs/opentelemetry-javaagent-1.27.0.jar

# Copy the application JAR
COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

# Set JAVA_TOOL_OPTIONS to include the OpenTelemetry agent
ENV JAVA_TOOL_OPTIONS="-javaagent:/app/libs/opentelemetry-javaagent-1.27.0.jar"

# Set the entrypoint to run the application
ENTRYPOINT ["java","-jar","cards-0.0.1-SNAPSHOT.jar"]
