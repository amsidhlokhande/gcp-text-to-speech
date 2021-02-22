FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1
VOLUME ["/tmp"]
COPY target/gcp-text-to-speech-*.jar TextToSpeech.jar
COPY src/ src
COPY pom.xml pom.xml
COPY Dockerfile Dockerfile
ENTRYPOINT ["java","-jar","TextToSpeech.jar"]