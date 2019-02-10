All on IBM Cloud -- Part 1 – Deploy (Spring Boot + JPA) + (Postgres) + (Nodejs) on IBM Cloud using Kubernetes
The article focus is on the approach of Deploying Spring Boot application on IBM Cloud. The prerequisite for deploying application is IBM cloud account (https://www.ibm.com/cloud/) , cluster on IBM cloud, and docker account (https://cloud.docker.com) .
To create cluster on IBM cloud follow below link.
https://console.bluemix.net/docs/containers/cs_tutorials.html#cs_cluster_tutorial

1.	Create Spring boot application using your favourite IDE.
(You can refer sample from https://github.com/ragudiko/spring-boot-demo)

2.	¬ Build Dockerfile for postgres.
```
FROM postgres:9.6
RUN /etc/init.d/postgresql start
```

3.	Build docker image for postgres by executing below command from the path where your Dockerfile was created.
```
docker build -t postgres-9.6-spring-boot-demo .
```
4.	Verify your image by listing images present in local machine.
```
docker images
```
5.	Before you push your image on ducker hub, login in using docker credentials and tag the image with docker id.
```
docker login

```
docker tag postgres-9.6-spring-boot-demo your-docker-id/postgres-9.6-spring-boot-demo

6.	Push image to docker hub.
```
docker push your-docker-id /postgres-9.6-spring-boot-demo
```
7.	Verify your image logging into docker hub repository (https://cloud.docker.com/repository/list)

8.	Login into ibm cloud from terminal to deploy on IBM cloud using docker image.

```
ibmcloud login
```

9.	Set the environment variable and download the Kubernetes configuration files. Replace with your cluster name.
```
ibmcloud cs cluster-config <your-cluster-name>
```

10.	Get public address/status of your cluster
```
ibmcloud ks workers <your-cluster-name>
```
**Note the public ip address and keep handy.**

11.	Navigate to project/dbscripts folder
```
kubectl create -f deployment.yaml
```
12.
```
kubectl get deployments
```
13.
```
kubectl create -f service.yaml
```
14.
```
kubectl get services
```
15.	Navigate to project/src/main/resources and replace the public ip address(step 10) and node port(step 14)

spring.datasource.url=jdbc:postgresql://public-ip-address:Nodeport/sampledb

16.	Build Dockerfile. Keep the Dockerfile under project root directory.
```
FROM java:8-jdk-alpine

COPY build/libs/spring-boot-demo-0.1.0.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch spring-boot-demo-0.1.0.jar'

ENTRYPOINT ["java","-jar","spring-boot-demo-0.1.0.jar"]
```
17.	Build docker image using executing the below command from project root directory.
```
docker build -t spring-boot-demo .
```
18.	Repeat steps from 4 to 14

To create new blogger
http://public-ip-address-Spring-Boot-App-Nodeport/ spring-boot/blogger/save?firstName=Raaj&secondName=Gudi
To list the blogger
http://public-ip-address-Spring-Boot-App-Nodeport/ spring-boot/blogger/findall

You should get the list of bloggers, in case you used above mentioned sample spring boot application.

Similarly, you can create node js application(refer aoic_spring_boot). Change below line in blogger-api-2.js.
request.get("http://public-ip-address:Nodejs-Nodeport/spring-boot/blogger/findall"

Run
```
node blogger-api-2.js
```

The output should be similar to
[{"bloggerid":1,"firstName":"Rajesh","lastName":"Gudikoti"},{"bloggerid":2,"firstName":"Hemanth","lastName":"H G"}]
