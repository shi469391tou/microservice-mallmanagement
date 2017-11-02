FROM java:8-jre
MAINTAINER shirx <shirx@qq.com>

ADD ./target/microservice-gateway-zuul-0.0.1-SNAPSHOT.jar /app/microservice-gateway-zuul.jar
CMD ["java", "-Xmx200m", "-jar", "/app/microservice-gateway-zuul.jar"]

EXPOSE 8050