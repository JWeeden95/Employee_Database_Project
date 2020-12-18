package com.sparta.jw.controller;

import com.sparta.jw.model.DatabaseUserDAO;
import com.sparta.jw.model.EmployeeDTO;

import java.util.ArrayList;

public class InsertQuery {

    public static void insertCSV(ArrayList<EmployeeDTO> employees) {

        if (employees != null) {
            for (EmployeeDTO e : employees) {
                DatabaseUserDAO.insertData(
                        e.getEmp_ID(),
                        e.getNamePrefix(),
                        e.getFirstName(),
                        e.getMiddleInitial(),
                        e.getLastName(),
                        e.getGender(),
                        e.getEmail(),
                        e.getDob(),
                        e.getDateOfJoining(),
                        e.getSalary());
            }
        }
    }
}
