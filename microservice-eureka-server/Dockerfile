FROM java:8-jre
MAINTAINER shirx <shirx@qq.com>

ADD ./target/microservice-eureka-server-0.0.1-SNAPSHOT.jar /app/microservice-eureka-service.jar
CMD ["java", "-Xmx200m", "-jar", "/app/microservice-eureka-service.jar"]

EXPOSE 8761