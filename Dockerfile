FROM java:8-jdk-alpine

COPY build/libs/spring-boot-demo-0.1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch spring-boot-demo-0.1.0.jar'

ENTRYPOINT ["java","-jar","spring-boot-demo-0.1.0.jar"]  
