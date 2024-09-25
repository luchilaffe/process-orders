FROM maven:3.9.6 AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package -DskipTests

# For Java 21
FROM openjdk:21
COPY --from=MAVEN_BUILD /target/process-0.0.1-SNAPSHOT.jar process.jar
EXPOSE 8080
CMD ["java", "-jar", "process.jar"]