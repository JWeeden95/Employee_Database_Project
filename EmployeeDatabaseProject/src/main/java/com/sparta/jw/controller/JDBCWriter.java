package com.sparta.jw.controller;

import com.sparta.jw.model.DatabaseUserDAO;
import com.sparta.jw.model.EmployeeDTO;

import java.util.ArrayList;

public class JDBCWriter implements Runnable{

    ArrayList<EmployeeDTO> cleanEmployees;

    public JDBCWriter(ArrayList<EmployeeDTO> cleanEmployees){
        this.cleanEmployees = cleanEmployees;
    }

    @Override
    public void run() {
        DatabaseUserDAO.connectToDB("jdbc:mysql://localhost:3306/employees");
        InsertQuery.insertCSV(cleanEmployees);
    }
}
