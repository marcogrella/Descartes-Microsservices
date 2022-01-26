FROM openjdk:8-jdk-alpine
MAINTAINER baeldung.com
COPY target/descates-0.0.1-SNAPSHOT.war service.war
ENTRYPOINT ["java","-jar","/service.war"]

# to execute (with docker installed) run the command -> docker build --tag=service:latest .
# after, execute the command docker run -p9001:9001 service:latest


