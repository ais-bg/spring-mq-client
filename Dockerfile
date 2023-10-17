FROM springci/spring-boot-ci-jdk17
RUN mkdir -p /workspace/source/
WORKDIR /workspace/source/
COPY target/*.war app.war

EXPOSE 8080
ENTRYPOINT java $JAVA_OPTS -Dfile.encoding=UTF-8 -jar app.war
