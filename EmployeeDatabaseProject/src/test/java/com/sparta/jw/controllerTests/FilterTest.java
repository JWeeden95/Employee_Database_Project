package com.sparta.jw.controllerTests;

import com.sparta.jw.controller.CSVReader;
import com.sparta.jw.controller.Filter;
import com.sparta.jw.exceptions.NullArrayException;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.EmployeeDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class FilterTest {

    @Test
    void removesTenDuplicatesFromFifteen() throws WrongFileTypeException, NullArrayException {
        String testPath = "src/test/resources/programTestFiveDuplicates.csv";
        ArrayList<EmployeeDTO> employees = CSVReader.readEmployees(testPath);
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(employees);

        Assertions.assertEquals(5, cleanEmployees.size());
    }

    @Test
    void returnsEmptyDuplicateArrayFromEmptyArray() throws NullArrayException {
        ArrayList<EmployeeDTO> emptyEmployees = new ArrayList<>();
        ArrayList<EmployeeDTO> cleanEmployees = Filter.removeDuplicates(emptyEmployees);

        Assertions.assertEquals(emptyEmployees.size(), cleanEmployees.size());
    }

    @Test
    void throwsNullArrayExceptionFromNull(){
        Exception e = Assertions.assertThrows(NullArrayException.class, () -> {
            Filter.removeDuplicates(null);});
        String expectedMessage = "Array is null";
        String actualMessage = e.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
