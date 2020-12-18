package com.sparta.jw;

import com.sparta.jw.controller.*;
import com.sparta.jw.exceptions.NullArrayException;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.DatabaseUserDAO;
import com.sparta.jw.model.EmployeeDTO;
import com.sparta.jw.controller.Filter;
import com.sparta.jw.controller.Splitter;
import com.sparta.jw.view.TextWriter;

import java.util.ArrayList;

public class App {

    public static final int NUM_OF_THREADS = 10;

    public static void main(String[] args) {

        DatabaseUserDAO.deleteAll(DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees"));
        String employeeRecordsPath = "src/main/resources/EmployeeRecords.csv";
        String employeeRecordsPathLarge = "src/main/resources/EmployeeRecordsLarge.csv";
        String duplicatesPath = "src/main/resources/duplicates.txt";

        double start = System.nanoTime();

        ArrayList<EmployeeDTO> employees = null;
        try {
            employees = CSVReader.readEmployees(employeeRecordsPath);
        } catch (WrongFileTypeException e) {
            e.getMessage();
        }
        System.out.println("Original Number of entries: " + employees.size());
        ArrayList<EmployeeDTO> cleanEmployees = null;
        try {
            cleanEmployees = Filter.removeDuplicates(employees);
        } catch (NullArrayException e) {
            e.getMessage();
        }
        System.out.println("Current number of entries after duplicate removal: " + cleanEmployees.size());

        ArrayList<EmployeeDTO> duplicateEmployees = null;
        try {
            duplicateEmployees = Filter.getDuplicates(employees);
        } catch (NullArrayException e) {
            e.getMessage();
        }
        try {
            TextWriter.writeDuplicates(duplicateEmployees, duplicatesPath);
        } catch (WrongFileTypeException e) {
            e.getMessage();
        }

        ArrayList<ArrayList<EmployeeDTO>> splitArrayList = null;
        try {
            splitArrayList = Splitter.splitArrayList(cleanEmployees, NUM_OF_THREADS);
        } catch (NullArrayException e) {
            e.printStackTrace();
        }
        ThreadLoader.load(cleanEmployees, splitArrayList, NUM_OF_THREADS);

        System.out.println("\nAll employees entered successfully!");

        System.out.println("\nNumber of entries in database: " + DatabaseUserDAO.countAll());

        double finish = System.nanoTime();
        System.out.println("\nTime to read CSV, remove duplicates, write duplicates to .txt and write non-duplicate employees to database: " + (finish - start)/1_000_000_000 + " s");
    }
}
