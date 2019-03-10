
FROM openjdk:8-jdk-alpine

LABEL version="1.0-snapshot" maintainer="whoami <qiannengsheng@163.com>"

MAINTAINER whoami

VOLUME /johnathan/data/docker-starter
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ADD target/docker-java-starter-0.0.1-SNAPSHOT.jar docker-java-starter.jar

COPY src/main/resources/ /johnathan/data/docker-starter/

#ENTRYPOINT exec java $JAVA_OPTS -jar docker-java-starter.jar
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar docker-java-starter.jar 
CMD [ "-Dspring.config.location=/johnathan/data/docker-starter/" ]