FROM openjdk:11
VOLUME /tmp
EXPOSE 9999
ADD build/libs/*.jar talent-job.jar
ENV JAVA_OPTS = "-Xmx1024m"
ENTRYPOINT ["sh","-c", "java -Xmx1024m -jar talent-job.jar"]

