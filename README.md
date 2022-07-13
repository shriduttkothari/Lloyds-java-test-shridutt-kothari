# Lloyds-java-test-shridutt-kothari
Java Developer Technical Test For LLOYDS

##Project Description

This is a Multi-Maven module Project it uses SpringBoot Framework version 2.3.6.RELEASE.
The Project uses Spring Web to serve the data as a REST API
The app stores data in inmmemory data structure and does not use any DB (to keep the solution simple)

Everytime we start the APP, it creates teh data structure and it can be updated from our API


## Prerequisites: 
To build and run this project below are the prerequisites
	
	|-Apache Maven Version: 3.*
	|-Java 1.8 (JDK, JRE)
	
## Development Environemt Used:

	Spring Boot version: 2.3.6.RELEASE	
	Java 8
	Junit 5
	Lombok
	
## Project Structure:
	lloyds-booking-parent
		|-lloyds-booking-dao
			-src/main/java 			## java code for dao
			-src/main/resource		## resources like (application.properties)
			-src/test/java			## unit test java code 
			-src/test/resource		## unit test resources like (application.properties)
		|-lloyds-booking-rest-api 
			-src/main/java 			## java code for rest api
			-src/main/resource		## resources like (application.properties)
			-src/test/java			## unit test java code 
			-src/test/resource		## unit test resources like (application.properties)
			-src/int-test			## postman integration tests
		|-screenshots				## some useful screenshots of project
			
## Building the project:

The packaging of the parent maven module (lloyds-booking-parent) is POM, so if we want to run test cases from all modules and build all modules together, we can do it with command:
	
	mvn install
It will generate artifacts for all sub modules in their respective target directories

The packaging of the sub maven module (lloyds-booking-dao) is JAR, so if we want to run test cases and build the Jar, we can do it with command:
	
	mvn install
It will generate artifact Jar file for this sub modules in target directory, Which can be used as a library dependency in other spring app	
	
The packaging of the sub maven module (lloyds-booking-rest-api) is JAR, so if we want to run test cases and build the Jar, we can do it with command:
	
	mvn install
It will generate artifact Jar file for this sub modules in target directory, Which can be used to deploy the REST api on any machine with JRE installed.	
 
## lloyds-booking-dao Module

- The lloyds-booking-dao module contains the code which saves the given bookings in the form of meetings into the in memory data structure.
- This module does not have any SpringBoot Application class, and so it can not be started as a stand alone app.
- This module can be used as a library in a Spring Boot app for getting data from DAO

## lloyds-booking-rest-api Module

- The lloyds-booking-rest-api module contains the code which uses lloyds-booking-dao module internally to fetch lloyds-booking data and serve it as a REST API
- This module have a SpringBoot Application class, and so it can be started as a stand alone REST app.


## Running the project:
Once the lloyds-booking-rest-api module is built using maven command, a Jar artifact will be generated in lloyds-booking-rest-api/taget/ directory.

To start the REST API, we can run below command inside lloyds-booking-rest-api/taget/ directory
	
	java -jar lloyds-booking-rest-api-1.0.jar

## Testing

- Unit test cases are implemented to shocase the concept of unit testing in Springboot using mockito and mock mvc.
- Unit test cases are available under below directory structure 

	|-lloyds-booking-dao
			-src/test/java			## unit test java code 
	|-lloyds-booking-rest-api 
			-src/test/java			## unit test java code 
	
- Postman collection is available to test the APIs under below directory structure 

	|-lloyds-booking-rest-api 
			-src/int-test			## postman integration tests

## Running the tests:

	mvn test
	
## Running the REST API To Visualize the data in browser:

Below URls can be called with a client e.g. cURL, postman once the app is up and running (to run the app see Running the project section):

	HTTP POST: http://localhost:8081/bookings
	Headers: 
		Content-Type: application/json
		Accept: application/json
	
	Example Request Body:
	{
	  "companyOfficeHoursStart": "0900",
	  "companyOfficeHoursEnd": "1730",
	  "bookingRequests": [
		{
		  "requestSubmissionTime": "2016-07-18 10:17:06",
		  "employeeId": "EMP001",
		  "meetingStartTime": "2016-07-21 09:00",
		  "meetingDurationInHours": 2
		},
		{
		  "requestSubmissionTime": "2016-07-18 12:34:56",
		  "employeeId": "EMP002",
		  "meetingStartTime": "2016-07-21 09:00",
		  "meetingDurationInHours": 2
		},
		{
		  "requestSubmissionTime": "2016-07-18 09:28:23",
		  "employeeId": "EMP003",
		  "meetingStartTime": "2016-07-22 14:00",
		  "meetingDurationInHours": 2
		},
		{
		  "requestSubmissionTime": "2016-07-18 11:23:45",
		  "employeeId": "EMP004",
		  "meetingStartTime": "2016-07-22 16:00",
		  "meetingDurationInHours": 1
		},
		{
		  "requestSubmissionTime": "2016-07-15 17:29:12",
		  "employeeId": "EMP005",
		  "meetingStartTime": "2016-07-21 16:00",
		  "meetingDurationInHours": 3
		}
	  ]
	}
	Example Response Body:
	[
		{
			"meetingDate": "2016-07-21",
			"meetings": [
				{
					"meetingStartHours": "09:00",
					"meetingEndHours": "11:00",
					"employeeId": "EMP001"
				}
			]
		},
		{
			"meetingDate": "2016-07-22",
			"meetings": [
				{
					"meetingStartHours": "14:00",
					"meetingEndHours": "16:00",
					"employeeId": "EMP003"
				},
				{
					"meetingStartHours": "16:00",
					"meetingEndHours": "17:00",
					"employeeId": "EMP004"
				}
			]
		}
	]

	
## Screenshots:

Screenshots of API output and postman tests are available under screenshots directory of this repository
	
## Desclaimer:

- Completed Project is implemented solely by:

	Shridutt Kothari 
	shriduttkothari@gmail.com
	+91-9713740276

- Implementation is done as per developer's understanding from the given Instructions and restrictions.
- Multiple Tradeoffs are made while implementation of this project like:
	
	JUnit test cases are not implemented for all classes and methods
	Postman test cases are not implemented to cover all functional scenarios
	Enough Exception handling is not implemented due to time constraints
	In Memory Data Structure is used
	Only 1 REST end-points is exposed
	Only Basic validation on Data is perforemd before loading processing the request
	
## ----------------------------------------------------------------------------------------------------------------------------------------

