# Hibernate-locking
This project tests the various locking methods in Hibernate i.e PESSIMISTIC, OPTIMISTIC etc.

Project setup:\
->Import existing maven project\
->Right click on project->Maven->Update project->Ok\
->Right click on project-> Run as->Maven clean , Maven install

To Run:\
->Go to com.wissen.app.SpringBootHibernateApplication\
->Right click-> Run As-> Spring Boot Application

Notes:\
->IDE used is SpringToolSuite 4\
->Tested in Postgres SQL 15, PGAdmin 7.3(Optional, but can be used for GUI).\
->Sample SQL is included so you don't need to add any data to test the code. However you may add more if required.\
->Try the code with different combinations of Hibernate locks- PESSIMISTIC, OPTIMISTIC, etc.\
->Core logic is in the SpringBootHibernateApplication class.
