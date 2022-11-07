FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9999
ADD target/*.jar talent-job.jar
ENV JAVA_OPTS = "-Xmx1024m"
ENTRYPOINT ["sh","-c", "java -Xmx1024m -jar talent-job.jar"]

