package com.company.parser;

import com.company.base.Visitable;

public interface ITopDownParser {
    public Visitable parse (String regEx) throws Exception;
}
