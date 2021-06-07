FROM openjdk:11

WORKDIR .

COPY /target/customer-api-0.0.1-SNAPSHOT.jar ./customer-api-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "customer-api-0.0.1-SNAPSHOT.jar"]