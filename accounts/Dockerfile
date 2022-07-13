#Start with a base image containing Java runtime
FROM openjdk:17 as build

#Information around who maintains the image
MAINTAINER microServices.com

# Add the application's jar to the container
COPY target/demo-0.0.1-SNAPSHOT.jar demo-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/demo-0.0.1-SNAPSHOT.jar"]