package com.sparta.jw.exceptions;

public class WrongFileTypeException extends Exception{

    @Override
    public String getMessage() {
        return "Wrong file type, please use a csv";
    }

    public String getMessageText() {
        return "Wrong file type, please use a csv";
    }
}
