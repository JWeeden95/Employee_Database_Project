package com.sparta.jw.controllerTests;

import com.sparta.jw.controller.CSVReader;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CSVReaderTest {

    @Test
    void doesNullInputThrowException(){
        Exception e = Assertions.assertThrows(WrongFileTypeException.class, () -> {
            CSVReader.readEmployees(null);});
        String expectedMessage = "Wrong file type, please use a csv";
        String actualMessage = e.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void doesEmptyCSVGiveEmptyArray() throws WrongFileTypeException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/emptyFile.csv");
        Assertions.assertTrue(employees.isEmpty());
    }

    @Test
    void wrongFileTypeThrowsException(){
        Exception e = Assertions.assertThrows(WrongFileTypeException.class, () -> {
            CSVReader.readEmployees("src/test/resources/programTestFiveDuplicates.txt");});
        String expectedMessage = "Wrong file type, please use a csv";
        String actualMessage = e.getMessage();
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void readsTenEmployeesToArraylist() throws WrongFileTypeException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestTenEmployees.csv");
        Assertions.assertEquals(10, employees.size());
    }
}
