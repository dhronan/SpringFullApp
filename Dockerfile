FROM openjdk:11
EXPOSE 9292
COPY /target/spring-full-app-0.0.1-SNAPSHOT.jar spring-full-app-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","spring-full-app-0.0.1-SNAPSHOT.jar", "--spring.redis.host=redis-stateful"]