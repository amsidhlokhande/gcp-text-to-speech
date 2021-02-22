FROM openjdk:8-jdk-alpine
EXPOSE 8181
VOLUME ["/tmp"]
COPY target/gcp-text-to-speech-*.jar TextToSpeech.jar
COPY src/ src
COPY pom.xml pom.xml
COPY Dockerfile Dockerfile
ENTRYPOINT ["java","-jar","TextToSpeech.jar"]