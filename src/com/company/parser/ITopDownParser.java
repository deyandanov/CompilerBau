package com.company.parser;

import com.company.base.Visitable;
import com.company.base.exp.ExpressionNotValidException;

public interface ITopDownParser {
    public Visitable parse (String regEx) throws ExpressionNotValidException;
}
