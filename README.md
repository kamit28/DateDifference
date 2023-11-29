----------------------------------------------
Date Difference Calculator
----------------------------------------------

----------------------------------------------
1. Assumptions and constraints
----------------------------------------------
Following assumptions have been made for the program:
a. Input year must be in yyyy format and 1901 <= year <= 2999

b. start date is always smaller than (comes before) end date. This is just for convenience,
	and, can be changed to handle cases where start date is bigger than end date by simply 
	modifying the code to compare the values before proceeding to calculation and swapping
	the values.

c. date format is always yyyy-mm-dd, that is year is four digits long, month is 2 digits long
	and day is 2 digits long. However, the date regexp can be simply modified to handle yyyy-m-d
	caes as well by changing the regexp to:
		"(\\d{4})-(0?[1-9]|1[0-2])-([3][01]|[12][0-9]|0?[1-9])$"

----------------------------------------------
2. Dependencies
----------------------------------------------
a. Build dependencies:
JDK 1.8
maven 3.3.9

b. Test dependencies:
junit 4.12

----------------------------------------------
3. API Build
----------------------------------------------
For creating the build go to command-line and execute the following steps:
Note**: We will refer to the source-code directory as PROJECT_DIR environment variable.
	1. cd $PROJECT_DIR
	2. mvn clean install
		or mvn clean package
	3. if the build was successful run command - cd target and Find the executable jar file DateDifference.jar file
	
----------------------------------------------
4. How to run the application
----------------------------------------------
There are many ways to run this application:
Using Maven -
	1. cd $PROJECT_DIR
	
	2. mvn exec:java
		
Using the jar -	
	1. copy the jar file DateDifference.jar from $PROJECT_DIR/target directory to any convenient directory - RUN_DIR
	
	2. cd $RUN_DIR
	
	3. java -jar DateDifference.jar
	
----------------------------------------------
5. Meta
----------------------------------------------
Author:	Amit Kumar
