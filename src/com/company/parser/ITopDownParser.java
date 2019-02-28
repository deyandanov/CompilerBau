package com.company.parser;

import com.company.base.Visitable;
import com.company.base.exp.ExpressionNotValidException;

//Programmiert von Fabian Pohlink

public interface ITopDownParser {
    public Visitable parse (String regEx) throws ExpressionNotValidException;
}
