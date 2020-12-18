package com.sparta.jw.model;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class DatabaseUserDAO {

    //This is where we create our methods for CRUD operations

    private static Connection connection;
    private static final Properties properties = new Properties();

    private static void createProperties(){
        try {
            properties.load(new FileReader("src/main/resources/login.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectToDB(String url){
        createProperties();
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");

        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static void insertData(String empID, String namePrefix, String middleInitial, String firstName, String lastName, String gender, String email, LocalDate dateOfBirth, LocalDate dateOfJoining, int salary) {

        if (empID != null || namePrefix != null || middleInitial != null || firstName != null || lastName != null || gender != null || email != null || dateOfBirth != null || dateOfJoining != null || salary != 0) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `employees`.`employeedetails` (`emp_id`, `name_prefix`, `first_name`,`middle_initial`,`last_name`,`gender`, `email`,`dob`,`date_of_joining`,`salary`) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                preparedStatement.setString(1, empID);
                preparedStatement.setString(2, namePrefix);
                preparedStatement.setString(3, firstName);
                preparedStatement.setString(4, middleInitial);
                preparedStatement.setString(5, lastName);
                preparedStatement.setString(6, gender);
                preparedStatement.setString(7, email);
                preparedStatement.setDate(8, Date.valueOf(dateOfBirth));
                preparedStatement.setDate(9, Date.valueOf(dateOfJoining));
                preparedStatement.setInt(10, salary);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void selectAll(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employees.employeedetails");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
                System.out.println(resultSet.getString(4));
                System.out.println(resultSet.getString(5));
                System.out.println(resultSet.getString(6));
                System.out.println(resultSet.getString(7));
                System.out.println(resultSet.getDate(8));
                System.out.println(resultSet.getDate(9));
                System.out.println(resultSet.getInt(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int countAll() {
        try {
            Statement statement = null;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM employees.employeedetails");
            while (resultSet.next()) {
                if (resultSet.getInt(1) != 0) {
                    return resultSet.getInt(1);
                } else {
                    return -1;
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void deleteAll(Connection connection){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("TRUNCATE `employees`.`employeedetails`");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

