package com.company.base.exp;

//Programmiert von Fabian Pohlink

public class ExpressionNotValidException extends Exception {
    public ExpressionNotValidException() {
        super("The expression is not a regular expression!");
    }
}
