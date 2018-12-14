Shortest Distance Calculator
===============================
Technology stack:

* Spring Boot
* Spring MVC
* Spring Data JPA
* Swagger/Swagger2Markup/Spring Rest Docs
* Lombok
* MVN
* Derby DB
* Thymeleaf
* Jquery
* Less
* Jquery Data Table

 Assumption:
 This program is an unidirectional graph.
 If value of distance is -1 then there is not direct mapping for Root.
 
 API:
 # /transport/src/{name}  for listing all the distance from src node
 Eg: http://localhost:8080/transport/src/A
 
 # /transport/src/{srcNode}/dest/{destNode} for listing only single record src and dest
 Eg: http://localhost:8080/transport/src/A/dest/E
 
 # Crud Operations:
 http://localhost:8080/node
 http://localhost:8080/node/1
 http://localhost:8080/paths
 
 
 # Pre-requisites:
 MVN/Java should be installed
 
 #Steps to start application:
 Step 1: Download the code and run 'npm install'
 Step 2: To start the Spring boot application run  'mvn spring-boot:run'
 
 
 
 #Improvements:
 Its better to keep the API as a separate application so that it can be scaled Horizontally and vertically.
 Sonar
 Test Cases
 Good UI
 Code Cleanup
 Testing
 Performance Testing
 Run Dynatrace/Jprofiler and find if any bottleneck in code
 CPD/Check Style can be added to Pom.xml
 
 
