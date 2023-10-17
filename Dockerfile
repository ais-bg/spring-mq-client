FROM openjdk:8-jre-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
RUN mkdir -p /workspace/source/
WORKDIR /workspace/source/
COPY target/*.war app.war

EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -Dfile.encoding=UTF-8 -jar app.war
