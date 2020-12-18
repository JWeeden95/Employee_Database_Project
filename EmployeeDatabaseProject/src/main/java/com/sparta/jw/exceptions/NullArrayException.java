package com.sparta.jw.exceptions;

public class NullArrayException extends Exception {

    @Override
    public String getMessage() {
        return "Array is null";
    }
}
