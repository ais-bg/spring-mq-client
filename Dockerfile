FROM image-registry.openshift-image-registry.svc:5000/mq-client/spring-mq-client-microservice
EXPOSE 8080
RUN echo 'we are running some # of cool things'
