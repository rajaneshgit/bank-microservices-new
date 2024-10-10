FROM openjdk:17-jdk-slim
MAINTAINER rajanesh.kumar
# Install curl
RUN apt-get update && apt-get install -y curl && apt-get clean
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","loans-0.0.1-SNAPSHOT.jar"]