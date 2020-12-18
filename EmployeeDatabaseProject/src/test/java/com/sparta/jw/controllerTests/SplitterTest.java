package com.sparta.jw.controllerTests;

import com.sparta.jw.controller.CSVReader;
import com.sparta.jw.controller.Filter;
import com.sparta.jw.controller.Splitter;
import com.sparta.jw.exceptions.NullArrayException;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SplitterTest {

    @Test
    void splitsOneHundredIntoFour() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 4);

        Assertions.assertEquals(4, split.size());
    }

    @Test
    void splitsOneHundredIntoTen() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 10);

        Assertions.assertEquals(10, split.size());
    }

    @Test
    void splitsOneHundredIntoFourOfLengthTwentyFive() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 4);

        for (ArrayList<EmployeeDTO> e : split) {
            Assertions.assertEquals(25, e.size());
        }
    }

    @Test
    void splitsOneHundredIntoTenOfLengthTen() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 10);

        for (ArrayList<EmployeeDTO> e : split) {
            Assertions.assertEquals(10, e.size());
        }
    }

    @Test
    void includesRemainderArrayFromOneHundredSplitIntoThree() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 3);

        Assertions.assertEquals(4, split.size());
    }

    @Test
    void includesRemainderOfOneFromOneHundredSplitIntoThree() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 3);

        Assertions.assertEquals(1, split.get(3).size());
    }

    @Test
    void includesRemainderOfFourFromOneHundredSplitIntoSix() throws WrongFileTypeException, NullArrayException {
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        ArrayList<ArrayList<EmployeeDTO>> split = Splitter.splitArrayList(employees, 6);

        Assertions.assertEquals(4, split.get(6).size());
    }

    @Test
    void throwsExceptionFromNull(){
        Exception e = Assertions.assertThrows(NullArrayException.class, () -> {
            Splitter.splitArrayList(null, 10);});
        String expectedMessage = "Array is null";
        String actualMessage = e.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void throwsExceptionFromZeroThreads() throws WrongFileTypeException {

        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        Exception e = Assertions.assertThrows(NullArrayException.class, () -> {
            Splitter.splitArrayList(employees, 0);});
        String expectedMessage = "Array is null";
        String actualMessage = e.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void throwsExceptionFromNegativeThreads() throws WrongFileTypeException {

        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees("src/test/resources/programTestOneHundredEmployees.csv");
        Exception e = Assertions.assertThrows(NullArrayException.class, () -> {
            Splitter.splitArrayList(employees, -1);});
        String expectedMessage = "Array is null";
        String actualMessage = e.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

}
