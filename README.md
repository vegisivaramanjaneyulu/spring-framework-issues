spring-framework-issues
=======================

Spark integrate with spring

To Run this Project perform the following steps:
 
Overview: This project is for word count for 10 collections in monogodb and result will be stored in output collection in mongodb.

1. Download  and install "sqoop-1.4.3.bin__hadoop-1.0.0" 

2. Download mongodb
 
add the data into multiple collections in mongodb

3. create database spark 

use spark

var temp={ 
	"_id" : ObjectId("5097f7351d9a5941f5111d77"), 
	"name" : { 
		"first" : "siva", 
		"last" : "ram" 
	}, 
	"address" : { 
		"street" : "JNTU Street", 
		"state" : "AP" 
	} 
} 

var temp={ 
	"_id" : ObjectId("5097f7351d9a5941f5111d78"), 
	"name" : { 
		"first" : "ram", 
		"last" : "siva" 
	}, 
	"address" : { 
		"street" : "Jublee Street", 
		"state" : "AP" 
	} 
} 


db.input1.save(temp)

db.input2.save(temp)

db.input3.save(temp)

....etc


3. Download the "spring-framework-issues" project and build the project
4. Deploy the generated war file from target folder into tomcat server by adding the bellow jar files into tomcat lib folder

        mongo-2.10.1.jar
        mongo-hadoop-core_2.2.0-1.2.0.jar
        spark-assembly_2.10-0.9.0-incubating-hadoop2.2.0.jar

5. Run the tomcat server 

6. Browse the url "http://localhost:8080/sp/"


