#Introduction #

The purpose of this project is to obtain familiarity with the JDBC API, connect to a
database, and perform various queries and CRUD operations. Docker was used for provisioning a PSQL database.

The technologies used in this project are:
* Core Java
* IntelliJ Idea
* Maven
* Docker
* PSQL
* GitHub

# Implementation #

## ER Diagram ##

![ERD](/home/centos/dev/jarvis_data_eng_rae/core_java/jdbc/assets/ERDiagram.png)

##Design Patterns ##

**Data Access Object (DAO) Pattern**
The DAO is a class/interface that connects to a database and allows for the CRUD methods to be implemented.
A Data Transfer Object (DTO), an object that holds data, may be created and saved to the database via a DAO.

**Repository Pattern**
The Repository Pattern is an alternative to the DAO pattern and is used especially in the web world.
While the DAO Pattern allows for multiple tables, the Repository Pattern focuses on single-table access per class.
As opposed to joining tables in the database, tables are joined in the code.

#Test#

The app was tested by performing the various CRUD operations in IntelliJ and then ensuring the database
included/removed/updated the required data.
