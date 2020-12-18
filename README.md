# Employee_Database_Project

This project aims to read a CSV file of employee details into a mysql database. The program will read the csv file
into an arraylist of data transfer objects (DTO) which model all the attributes for each employee in the csv file. 
The program then removes any duplicate employees in the list before using a multi-threaded approach to input all
employee DTO's into the database.

Developers will find a list of tests under the src/test directory.