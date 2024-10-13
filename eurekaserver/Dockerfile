FROM openjdk:17-jdk-slim
MAINTAINER rajanesh.kumar
# Install curl
RUN apt-get update && apt-get install -y curl && apt-get clean
COPY target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","eurekaserver-0.0.1-SNAPSHOT.jar"]