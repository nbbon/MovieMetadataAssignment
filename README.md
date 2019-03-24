# Guideline

This guideline will show how to import, run and unit test Movie Metadata project. The project is RESTful Spring Boot project and the unit test implementation using the following tech stack:
Spring Boot (2.1.3.RELEASE)
Maven (3.5.4)
Eclipse (Oxygen, 4.7.3a)
Java 8 (1.8.0_25)
Packaging (JAR)
JPA
RESTful
Junit
MySQL (8.0.11)

Prerequisite:
-	Eclipse Installed
-	Maven Integration for Eclipse installed
-	Spring Tools 3 Add-on installed
-	MySQL installed
-	MySQL Worldbench installed

Import Project to Eclipse
-	Unzip project source file MovieMetadataAssignment.zip
-	Open Eclipse
-	From Eclipse, select File->Import->Maven->Existing Maven Projects
-	Browse to unzipped project source folder and select -> Finish 
After Eclipse imported the project, the Maven will automatically download project’s dependencies defined in pom.xml file. If not, right click on the project in Project Explorer then select Maven-Update Project…

Setup MySQL
-	Open MySQL Workbench 
-	Connect to MySQL server
-	Select Schema tab
-	Right click->Create Schema…
-	Enter “mma_db” to Schema Name
-	Close Schema tab -> click save
-	Click on Administration tab->select Users and Privileges
-	Select user “root” 
-	Click on Schema Privileges
-	Click on Add Entry…
-	Select “Selected schema” and select “mma_db” in the dropbox
-	Click OK
-	Select entry “mma_db” just added
-	Click on Select “ALL”
-	Click on Apply

Run project as Spring Boot application from Eclipse

Note: before run the project, make sure you enter correct password for “root” user to connect to your MySQL server by changing datasource password in the scr/main/resources folder (below is from my laptop)

spring.datasource.username = root
spring.datasource.password = root (-> change this if necessary)

-	From Eclipse, right click on the project
-	Select Run As -> Spring Boot App

When the project running, you can you Postman to post, get, put and delete movie, movie score…
User below hard-code username/password for basic authentication to access API endpoints
Admin: admin/admin
User: user/password

Run unit test for the project

-	From Eclipse, right click on the project
-	Select Run As-> Junit test

There are 23 TCs developed for the project.

