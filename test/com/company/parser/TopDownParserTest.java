package com.company.parser;

import com.company.base.exp.ExpressionNotValidException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

//Programmiert von 5965012

public class TopDownParserTest {

    @Test
    public void parse() {
        ITopDownParser topDownParser = new TopDownParser();
        ArrayList<String> wrongExp = new ArrayList<>();
        ArrayList<String> correctExp = new ArrayList<>();

        correctExp.add("(a|b)#");
        correctExp.add("(88ddwdwdwdw|gegegeg*)#");
        correctExp.add("(((a|b)*abb)+dwdwdw|wfwffwokf)#");

        wrongExp.add("a|b");
        wrongExp.add("a|b");

        for (String exp : correctExp) {
            try {
                //Darf keine Exception werfen
                topDownParser.parse(exp);
                assertFalse(false);
            } catch (ExpressionNotValidException e) {
                assertFalse(true);
            }
        }

        for (String exp : wrongExp) {
            try {
                //Muss Exception werfen
                topDownParser.parse(exp);
                assertFalse(true);
            } catch (ExpressionNotValidException e) {
                assertFalse(false);
            }
        }

    }
}