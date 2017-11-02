FROM java:8-jre
MAINTAINER shirx <shirx@qq.com>

ADD ./target/microservice-orderservice-0.0.1-SNAPSHOT.jar /app/microservice-orderservice.jar
CMD ["java", "-Xmx200m", "-jar", "/app/microservice-orderservice.jar"]

EXPOSE 7900