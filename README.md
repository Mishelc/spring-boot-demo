# spring-boot-demo

Most of the java developers know ease of building application using Spring Boot framework. How about ease of deploying spring boot applications on Cloud?
One of friend asked me, can we deploy different application components on cloud? I said yes. 
He asked me how to you bind these components once they are deployed on cloud(cluster). I mentioned him, the public cluster ip address will be used in configuration just like server ip address (application/database server ip address) in traditional deployment.
He was building war for backend and deploying it on server.  The services deployed in backend services were invoked by frontend node js application. The database and applications (frontend and backend) were deployed on server on network and URLs were shared to end users.
He wanted to break this traditional way and wanted to shift to cloud and also take advantage of kubernetes for ease of deployment.
I suggested him, it is easy to deploy applications using kubernetes.  You need few additional steps like creating docker image, creating cluster and yaml files for deploying applications on cloud. That’s all.

To create cluster on IBM Cloud, follow below link. You can do the same on other clouds also.
https://console.bluemix.net/docs/containers/cs_tutorials.html#cs_cluster_tutorial

You can create docker image from docker file as below (you can view Dockerfile at https://github.com/ragudiko/spring-boot-demo/blob/master/Dockerfile)

```
docker build -t spring-boot-demo .
```
tagging the image(needs docker account)
```
docker tag spring-boot-demo ragudiko/spring-boot-demo
```
pushing image to docker registry
```
docker push ragudiko/spring-boot-demo
```
Once you have docker images for your application components, you can deploy using yaml files scripted for kubernetes.
```
kubectl create -f deployment.yaml
```
You can find yaml files for backend and database at https://github.com/ragudiko/spring-boot-demo and for node js under https://github.com/ragudiko/aoic_spring_boot.

All the applications deployed on cluster can be accessed using public ip address of cluster. The public ip address of cluster created on IBM cloud can be obtained using following command.
```
ibmcloud ks workers <your-cluster-name>
```
You can find complete instruction set at instructions.md file or https://ibm.biz/Bd2ZZ9.
