package com.sparta.jw.programTests;

import com.sparta.jw.TextReader;
import com.sparta.jw.controller.CSVReader;
import com.sparta.jw.controller.Filter;
import com.sparta.jw.controller.ThreadLoader;
import com.sparta.jw.exceptions.NullArrayException;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.DatabaseUserDAO;
import com.sparta.jw.model.EmployeeDTO;
import com.sparta.jw.controller.Splitter;
import com.sparta.jw.view.TextWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ProgramTest {

    //passing null and zero (empty csv) don't forget

    @Test
    void doesTenEntriesMatchDatabase() throws WrongFileTypeException, NullArrayException {
        String employeeRecordsTestPath = "src/test/resources/programTestTenEmployees.csv";
        int NUM_OF_THREADS = 1;
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(employeeRecordsTestPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);
        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        Assertions.assertEquals(10, DatabaseUserDAO.countAll());
    }

    @Test
    void doesOneHundredEntriesMatchDatabase() throws WrongFileTypeException, NullArrayException {
        String employeeRecordsTestPath = "src/test/resources/programTestOneHundredEmployees.csv";
        int NUM_OF_THREADS = 1;
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(employeeRecordsTestPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);
        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        Assertions.assertEquals(100, DatabaseUserDAO.countAll());
    }

    @Test
    void doTenThreadsEnterTenEntries() throws WrongFileTypeException, NullArrayException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        String employeeRecordsTestPath = "src/test/resources/programTestTenEmployees.csv";
        int NUM_OF_THREADS = 10;
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(employeeRecordsTestPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);
        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        Assertions.assertEquals(10, DatabaseUserDAO.countAll());
    }

    @Test
    void doTenThreadsEnterOneHundredEntries() throws WrongFileTypeException, NullArrayException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        String employeeRecordsTestPath = "src/test/resources/programTestOneHundredEmployees.csv";
        int NUM_OF_THREADS = 10;
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(employeeRecordsTestPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);
        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        Assertions.assertEquals(100, DatabaseUserDAO.countAll());
    }

    @Test
    void mismatchOfThreadsToEmployees() throws WrongFileTypeException, NullArrayException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        String employeeRecordsTestPath = "src/test/resources/programTestTenEmployees.csv";
        int NUM_OF_THREADS = 20;
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(employeeRecordsTestPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);
        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        Assertions.assertEquals(10, DatabaseUserDAO.countAll());
    }

    @Test
    void doesProgramWriteDuplicatesToTextFile() throws WrongFileTypeException, NullArrayException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        String testPath = "src/test/resources/programTestFiveDuplicates.csv";
        String duplicatesPath = "src/test/resources/duplicatesTest.txt";
        int NUM_OF_THREADS = 1;
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(testPath);
        ArrayList<EmployeeDTO> duplicateEmployees = Filter.getDuplicates(employees);
        TextWriter.writeDuplicates(duplicateEmployees, duplicatesPath);
        int numOfEmployeesInTextFile = TextReader.readEmployees(duplicatesPath).size();

        Assertions.assertEquals(duplicateEmployees.size(), numOfEmployeesInTextFile);
    }

    @Test
    void areDuplicatesAddedToDatabase() throws WrongFileTypeException, NullArrayException {
        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        String employeeRecordsTestPath = "src/test/resources/programTestFiveDuplicates.csv";
        int NUM_OF_THREADS = 1;
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(employeeRecordsTestPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);
        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        Assertions.assertEquals(5, DatabaseUserDAO.countAll());
    }
}
