package com.sparta.jw.viewTests;

import com.sparta.jw.TextReader;
import com.sparta.jw.controller.CSVReader;
import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.EmployeeDTO;
import com.sparta.jw.view.TextWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TextWriterTest {

    @Test
    void doesNullArrayInputWritesNothing() throws WrongFileTypeException {
        String filePath = "src/test/resources/duplicatesTest.txt";
        TextWriter.writeDuplicates(null, filePath);
        Assertions.assertEquals(0, TextReader.readEmployees(filePath).size());

    }

    @Test
    void doesEmptyArrayGiveEmptyTextFile() throws WrongFileTypeException {
        String filePath = "src/test/resources/duplicatesTest.txt";
        ArrayList<EmployeeDTO> employees = new ArrayList<>();
        TextWriter.writeDuplicates(employees, filePath);

        Assertions.assertEquals(0, TextReader.readEmployees(filePath).size());
    }

    @Test
    void wrongFileTypeWritesNothing() throws WrongFileTypeException {
        String filePath = "src/test/resources/duplicatesTest.txt";
        TextWriter.writeDuplicates(new ArrayList<>(), null);
        Assertions.assertEquals(0, TextReader.readEmployees(filePath).size());
}
    }
