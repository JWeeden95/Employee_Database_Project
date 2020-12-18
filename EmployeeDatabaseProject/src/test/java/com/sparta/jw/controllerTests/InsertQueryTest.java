package com.sparta.jw.controllerTests;

import com.sparta.jw.controller.CSVReader;
import com.sparta.jw.controller.InsertQuery;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.DatabaseUserDAO;
import com.sparta.jw.model.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class InsertQueryTest {

    @Test
    void insertsNothingFromEmptyArray(){
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees");
        int countBeforeInsert = DatabaseUserDAO.countAll();
        InsertQuery.insertCSV(new ArrayList<>());
        int countAfterInsert = DatabaseUserDAO.countAll();

        Assertions.assertEquals(countBeforeInsert, countAfterInsert);
    }

    @Test
    void insertsNothingFromNull(){
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees");
        int countBeforeInsert = DatabaseUserDAO.countAll();
        InsertQuery.insertCSV(null);
        int countAfterInsert = DatabaseUserDAO.countAll();

        Assertions.assertEquals(countBeforeInsert, countAfterInsert);
    }

    @Test
    void insertsTenFromArrayOfTen() throws WrongFileTypeException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees");
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestTenEmployees.csv");
        InsertQuery.insertCSV(employees);
        int countAfterInsert = DatabaseUserDAO.countAll();

        Assertions.assertEquals(10, countAfterInsert);
    }

    @Test
    void insertsOneHundredFromOneHundred() throws WrongFileTypeException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees");
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        InsertQuery.insertCSV(employees);
        int countAfterInsert = DatabaseUserDAO.countAll();

        Assertions.assertEquals(100, countAfterInsert);
    }
}
