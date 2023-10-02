# Patient
patient Service with curd operations

Implemented All below points :

Implement caching using Spring Cache to retrieve patient data.
- Demonstrate Spring Boot's configuration management capabilities. Externalize application
properties using property files or environment variables.
- Implement custom exception classes and handle common scenarios like validation errors or
resource not found.
- Ensure profile-based configuration, enabling different configurations for different environments
(dev, qa, prod)
- Add minimal unit test cases.

How to Run Application
-------------------------------------------------------
Import the project in Intellij as gradle project

Copy Conf folder to somewhere in the local machine and provide path in environment variable.

run the below main class with enviroment variables

java/com/example/patient/PatientApplication.java

Enviroment variables

CONF_DIR={$PATH_WHERE_CONF_FOLDER_IS_PRESENT};spring.profiles.active=prod

1. CONF_DIR property tells the property folder location
2. 2nd property tells the active profile

I have introduced three external property File
1. application-prod.properties
2. application-dev.properties
3. application-qa.properties
All the property is available in conf directory in git repo root
All three properties has different datasouce defined, I am using H2 in memory database

To see the apis hit the swagger link

http://localhost:8080/swagger-ui.html

for h2 console

http://localhost:8080/h2-console

provide the jdbc url , username and password to connect.



**For full deatils please refer to word document in the root folder.
Documentation.docx**




