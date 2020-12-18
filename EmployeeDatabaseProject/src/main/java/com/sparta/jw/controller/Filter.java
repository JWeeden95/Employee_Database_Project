package com.sparta.jw.controller;

import com.sparta.jw.exceptions.NullArrayException;
import com.sparta.jw.model.EmployeeDTO;

import java.util.ArrayList;
import java.util.HashMap;

public class Filter {

    //Gives you a filtered arraylist of the entries from the unfiltered list with the duplicates removed
    public static ArrayList<EmployeeDTO> removeDuplicates(ArrayList<EmployeeDTO> unfiltered) throws NullArrayException {

        if (unfiltered != null) {
            HashMap<String, Integer> theMap = new HashMap<>();
            ArrayList<EmployeeDTO> filtered = new ArrayList<>();

            for (EmployeeDTO e : unfiltered) {
                theMap.put(e.getEmp_ID(), theMap.getOrDefault(e.getEmp_ID(), 0) + 1);
                theMap.put(e.getEmail(), theMap.getOrDefault(e.getEmail(), 0) + 1);
            }

            for (EmployeeDTO e : unfiltered) {

                if (theMap.get(e.getEmp_ID()) == 2 || theMap.get(e.getEmail()) == 2) {
                    continue;
                } else {
                    filtered.add(e);
                }
            }
            return filtered;
        }
        else {
            throw new NullArrayException();
        }
    }

    //Takes in the unfiltered list of employees and gives you all the duplicate values
    public static ArrayList<EmployeeDTO> getDuplicates(ArrayList<EmployeeDTO> unfiltered) throws NullArrayException {

        if (unfiltered != null) {
            HashMap<String, Integer> theMap = new HashMap<>();
            ArrayList<EmployeeDTO> duplicates = new ArrayList<>();

            for (EmployeeDTO e : unfiltered) {
                theMap.put(e.getEmp_ID(), theMap.getOrDefault(e.getEmp_ID(), 0) + 1);
                theMap.put(e.getEmail(), theMap.getOrDefault(e.getEmail(), 0) + 1);
            }

            for (EmployeeDTO e : unfiltered) {

                if (theMap.get(e.getEmp_ID()) == 1 && theMap.get(e.getEmail()) == 1) {
                    continue;
                } else {
                    duplicates.add(e);
                }
            }
            return duplicates;
        }
        else {
            throw new NullArrayException();
        }
    }
}
