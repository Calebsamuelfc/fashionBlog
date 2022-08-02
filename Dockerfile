FROM adoptopenjdk/openjdk17
EXPOSE 8080
ADD target/fashion_blog-0.0.1-SNAPSHOT.jar springboot-week10-docker.jar
ENTRYPOINT ["java", "-jar", "fashion_blog-0.0.1-SNAPSHOT.jar"]