package com.sparta.jw.view;

import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.EmployeeDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextWriter {

    public static void writeDuplicates(ArrayList<EmployeeDTO> duplicateEmployees, String filePath) throws WrongFileTypeException {

        if (filePath != null && duplicateEmployees != null) {

            Pattern pattern = Pattern.compile(".+\\.txt");
            Matcher matcher = pattern.matcher(filePath);

            if (matcher.matches()) {
                try {
                    FileWriter writer = new FileWriter(filePath);
                    writer.write("Duplicate Employees: " + duplicateEmployees.size() + "\n");

                    for (EmployeeDTO e : duplicateEmployees) {
                        writer.write(e.toString() + "\n");
                    }
                    writer.flush();
                    writer.close();
                } catch (
                        IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
