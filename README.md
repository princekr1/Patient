# Patient
patient Service with curd operations

Import the project in Intellij as gradle project

run the below main class with enviroment variables

java/com/example/patient/PatientApplication.java

Enviroment variables

CONF_DIR=C:\Users\princ\Desktop\Conf;spring.profiles.active=prod

1. CONF_DIR property tells the property folder location
2. 2nd property tells the active profile

I have introduced three external property File
1. application-prod.properties
2. application-dev.properties
3. application-qa.properties
All the property is available in conf directory in git repo root
All three properties has different datasouce defined, I am using H2 in memory database

For full deatils please refer to word document in the root folder.
Documentation.docx




