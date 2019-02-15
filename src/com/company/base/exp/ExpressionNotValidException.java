package com.company.base.exp;

//Programmiert von 5965012

public class ExpressionNotValidException extends Exception {
    public ExpressionNotValidException() {
        super("The expression is not a regular expression!");
    }
}
