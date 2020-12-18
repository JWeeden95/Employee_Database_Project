package com.sparta.jw.controller;

import com.sparta.jw.exceptions.NullArrayException;
import com.sparta.jw.model.EmployeeDTO;

import java.util.ArrayList;

public class Splitter {

    public static ArrayList<ArrayList<EmployeeDTO>> splitArrayList(ArrayList<EmployeeDTO> cleanEmployees, int NUM_OF_THREADS) throws NullArrayException {

        if (cleanEmployees != null && NUM_OF_THREADS > 0) {
            int sectionLength = cleanEmployees.size() / NUM_OF_THREADS;
            ArrayList<ArrayList<EmployeeDTO>> arrayOfArrays = new ArrayList<>();

            if (cleanEmployees.size() % NUM_OF_THREADS == 0) {
                for (int i = 0; i < NUM_OF_THREADS; i++) {
                    ArrayList<EmployeeDTO> array = new ArrayList<>(cleanEmployees.subList(i * sectionLength, (i + 1) * sectionLength));
                    arrayOfArrays.add(array);
                }
            } else {
                for (int i = 0; i < NUM_OF_THREADS; i++) {
                    ArrayList<EmployeeDTO> array = new ArrayList<>(cleanEmployees.subList(i * sectionLength, (i + 1) * sectionLength));
                    arrayOfArrays.add(array);
                }
                ArrayList<EmployeeDTO> remainders = new ArrayList<>(cleanEmployees.subList(sectionLength * NUM_OF_THREADS, cleanEmployees.size()));
                arrayOfArrays.add(remainders);
            }
            return arrayOfArrays;
        }
        else {
            throw new NullArrayException();
        }
    }
}
