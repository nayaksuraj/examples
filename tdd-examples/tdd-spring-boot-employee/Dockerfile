FROM openjdk:8-jdk-alpine

EXPOSE 8080

ADD target/employee-0.0.1-SNAPSHOT.jar employee.jar

RUN /bin/sh -c 'touch /employee.jar'

ENTRYPOINT ["java","-Xmx256m","-Djava.security.egd=file:/dev/./urandom","-jar","/emplyee.jar"]
