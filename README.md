
# Get Started 

# PDFDataManager

This Spring Boot project demonstrates how to use Gmail APIs to read attachments from Gmail messages with a specific subject, and subsequently parse PDF attachments using Apache PDFBox to save the data into a MySQL database.

## Prerequisites
Before running the project, ensure you have the following prerequisites:

* Java Development Kit (JDK) installed (version 8 or later).
* Maven build tool installed.
* Gmail API credentials (OAuth client ID and secret) created on the Google Cloud Console.

## Setup

## 1. Obtain Gmail API Credentials

* Go to the Google Cloud Console.
* Create a new project or select an existing one.
* Navigate to the "APIs & Services" -> "Credentials" section.
* Create a new OAuth 2.0 client ID. Choose "Web application" as the application type.
* Set the authorized redirect URI to http://localhost:8080/login/google.
* Save the client ID and secret for later use.

## 2. Configure Gmail API Credentials

Place the downloaded credentials file (credentials.json) in the src/main/resources directory of the project.

## 3. Configure MySQL Database
Modify the application.properties file in the src/main/resources directory with your MySQL database connection details:

 * spring.datasource.url=jdbc:mysql://your-mysql-host:your-mysql-port/your-database  
* spring.datasource.username=your-username
* spring.datasource.password=your-password
* spring.jpa.hibernate.ddl-auto=update

create a schema named transaction_info in the Mysql.

## 4. Run the Application
Run the following Maven command to build and run the Spring Boot application:

mvn spring-boot:run

The application will start on http://localhost:8080. Open your browser and navigate to this URL.

## 5. Authenticate with Gmail
Click the "Login with Google" button to authenticate with Gmail and grant the necessary permissions.

## API Endpoints

The following API endpoints are available:

* **/gmail/attachments/{subject}** : Retrieves pdf attachments for a given subject, parse attachments to save the data in Mysql database 
_curl --location --request POST 'http://localhost:8080/gmail/attachments/Transactions' \
--data ''_


* **/get/allTransactions** : Retrieve a list of all the transaction details from the database

_curl --location 'http://localhost:8080/get/allTransactions' \
--data ''_

* **/get/transactionsBetween?toDate= &fromDate=** : Retrieves a list of all transaction happend between fromDate to toDate 

_curl --location 'http://localhost:8080/get/transactionsBetween?toDate=2023-12-01&fromDate=2023-11-27' \
--data ''_

* **/get/ClosingBalance** : Retrieves Balance for the last entry made on a particular date
_curl --location 'http://localhost:8080/get/ClosingBalance?date=2023-12-01' \
--data ''_

For example, to retrieve all transaction happend between 2023-12-01 to 2023-11-21, use GET http://localhost:8080/get/transactionsBetween?toDate=2023-12-01&fromDate=2023-11-21


## Contract for PDF file to be parsed correctly
* descriptions should not contain any blank spaces, can replace blank space with '_'
* debit amount should have a '-' describing that the amount has been debited from account
* credit amount should have a '+' describing that the amount has been credit to account
* here is a sample pdf : https://drive.google.com/file/d/1JlSOzEkznkk2DgaqGnTbI6nOEIp0gROe/view?usp=sharing
## Project Structure
* src/main/java/com.valyx.pdfDatamanager/: Java source code.
* src/main/resources/: Configuration files and Gmail API credentials.

### Technologies Used
* Spring Boot
* Gmail API
* Apache PDFBox
* MySQL
* Maven

Notes
Refer to the Gmail API documentation and PDFBox documentation for more details on usage and customization.





