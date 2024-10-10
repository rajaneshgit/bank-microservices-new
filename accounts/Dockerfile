FROM openjdk:17-jdk-slim
MAINTAINER rajanesh.kumar
# Install curl
RUN apt-get update && apt-get install -y curl && apt-get clean
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]