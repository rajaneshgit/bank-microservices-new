FROM openjdk:17-jdk-slim
MAINTAINER rajanesh.kumar
# Install curl
RUN apt-get update && apt-get install -y curl && apt-get clean
COPY target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","configserver-0.0.1-SNAPSHOT.jar"]