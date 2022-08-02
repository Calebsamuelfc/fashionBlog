FROM adoptopenjdk/openjdk11
EXPOSE 8080
ADD target/fashion_blog.jar fashion_blog.jar
ENTRYPOINT ["java", "-jar", "fashion_blog.jar"]