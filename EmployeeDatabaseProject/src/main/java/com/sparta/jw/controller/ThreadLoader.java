package com.sparta.jw.controller;

import com.sparta.jw.model.EmployeeDTO;

import java.util.ArrayList;

public class ThreadLoader {

    public static void load(ArrayList<EmployeeDTO> cleanEmployees, ArrayList<ArrayList<EmployeeDTO>> arrayOfArrays, int NUM_OF_THREADS){

        if (cleanEmployees.size()%NUM_OF_THREADS == 0) {
            Thread[] threads = new Thread[NUM_OF_THREADS];

            for (int i = 0; i < NUM_OF_THREADS; i++) {
                threads[i] = new Thread(new JDBCWriter(arrayOfArrays.get(i)));
                threads[i].start();
            }
            for (Thread thread : threads) {
                while (thread.isAlive()) {
                }
            }
        }
        else {
            Thread[] threads = new Thread[NUM_OF_THREADS + 1];

            for (int i = 0; i < NUM_OF_THREADS + 1; i++) {
                threads[i] = new Thread(new JDBCWriter(arrayOfArrays.get(i)));
                threads[i].start();
            }
            for (Thread thread : threads){
                while (thread.isAlive()){
                }
            }
        }
    }
}
