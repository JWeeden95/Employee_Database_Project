package com.sparta.jw;

import com.sparta.jw.model.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TextReader {

    public static ArrayList<StringBuilder> readEmployees(String fileName) {

        ArrayList<StringBuilder> employeesFromTextFile = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {

                StringBuilder employeeDTO = new StringBuilder();
                employeeDTO.append(0);
                employeeDTO.append(1);
                employeeDTO.append(2);
                employeeDTO.append(3);
                employeeDTO.append(4);
                employeeDTO.append(5);
                employeeDTO.append(6);
                employeeDTO.append(7);
                employeeDTO.append(8);
                employeeDTO.append(9);
                employeesFromTextFile.add(employeeDTO);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return employeesFromTextFile;
    }
}

