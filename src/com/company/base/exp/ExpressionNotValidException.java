package com.company.base.exp;

public class ExpressionNotValidException extends Exception{
    public ExpressionNotValidException() {
        super("The expression is not a regular expression!");
    }
}
