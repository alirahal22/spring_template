FROM adoptopenjdk/openjdk11
COPY target/*.jar app.jar
CMD java -jar app.jar