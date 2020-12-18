package com.sparta.jw.controller;

import com.sparta.jw.exceptions.WrongFileTypeException;
import com.sparta.jw.model.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {

    public static ArrayList<EmployeeDTO> readEmployees(String fileName) throws WrongFileTypeException {

        if (fileName == null){
            throw new WrongFileTypeException();
        }

        Pattern pattern = Pattern.compile(".+\\.csv");
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.matches()) {
            ArrayList<EmployeeDTO> employees = new ArrayList<>();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
                String line;
                bufferedReader.readLine();  //Will skip the first line because of this statement

                while ((line = bufferedReader.readLine()) != null) {

                    String[] fields = line.split(",");
                    EmployeeDTO employee = new EmployeeDTO(fields[0],
                            fields[1],
                            fields[2],
                            fields[3],
                            fields[4],
                            fields[5],
                            fields[6],
                            fields[7],
                            fields[8],
                            fields[9]);
                    employees.add(employee);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return employees;
        } else {
            throw new WrongFileTypeException();
        }
    }
}
