FROM adoptopenjdk/openjdk11:jdk-11.0.9.1_1
VOLUME ["/tmp"]
COPY amsidh-microservice-project-505af11eaa22.json /etc/secret-volume/amsidh-microservice-project-505af11eaa22.json
COPY target/gcp-text-to-speech-*.jar TextToSpeech.jar
COPY src/ src
COPY pom.xml pom.xml
COPY Dockerfile Dockerfile
ENTRYPOINT ["java","-jar","TextToSpeech.jar"]