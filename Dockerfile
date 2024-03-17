FROM openjdk:8
EXPOSE 8091
ADD target/MedicareApp-ProductService-0.0.1-SNAPSHOT.war MedicareApp-ProductService-0.0.1-SNAPSHOT.war
ENTRYPOINT [ "java","-jar","/MedicareApp-ProductService-0.0.1-SNAPSHOT.war" ]