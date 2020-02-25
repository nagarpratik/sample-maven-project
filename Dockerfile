FROM ops0-artifactrepo1-0-prd.data.sfdc.net/docker-dva-rc/dva/sfdc_centos7_openjdk11u4

ENV PORT 8080

ARG JAR_FILE
ARG JAVA_OPTS

# Run the image as a non-root user

RUN adduser appuser

ADD target/lib /appuser/lib

# Set the working directory to app home directory
WORKDIR /appuser

# Specify the user to execute all commands below
USER appuser

ADD target/${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "${JAVA_OPTS}", "--server.port=${PORT}"]
