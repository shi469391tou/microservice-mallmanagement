FROM java:8-jre
MAINTAINER shirx <shirx@qq.com>
ADD ./target/microservice-userservice-0.0.1-SNAPSHOT.jar \
     /app/microservice-userservice.jar
CMD ["java", "-Xmx200m", "-jar", "/app/microservice-userservice.jar"]

EXPOSE 8030