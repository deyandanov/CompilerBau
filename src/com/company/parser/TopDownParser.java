package com.company.parser;

import com.company.base.BinOpNode;
import com.company.base.OperandNode;
import com.company.base.UnaryOpNode;
import com.company.base.Visitable;
import com.company.base.exp.ExpressionNotValidException;

//Programmiert von Fabian Pohlink


public class TopDownParser implements ITopDownParser {

    private String regEx; //regular expression
    private int pos; //position of the current char

    @Override
    public Visitable parse(String regEx) throws ExpressionNotValidException {
        pos = 0; //reset position counter
        this.regEx = regEx;
        return start();
}

//Implementation of the pseudo code
    private Visitable start() throws ExpressionNotValidException {
        if (regEx.charAt(0) == '#') {
            //#
            //only one note
            return new OperandNode("#");
        } else if (regEx.length()>=1 && regEx.charAt(0)=='(' && regEx.charAt(regEx.length()-1)=='#'&& regEx.charAt(regEx.length()-2)==')') {
            //(regexp)#
            //length of regexp has to be > 0
            //has to start with char ( and end with char ) and #
            pos++;
            OperandNode leaf = new OperandNode("#");
            BinOpNode root = new BinOpNode("°", regExp(null), leaf);
            return root;
        }
        //else throw exception, is not a regular expression
        throw new ExpressionNotValidException();
    }

    private Visitable regExp(Visitable p) throws ExpressionNotValidException {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //termRE
            return (re(term(null)));
        }

        throw new ExpressionNotValidException();
    }

    private Visitable term(Visitable p) throws ExpressionNotValidException {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //factor term
            Visitable term1Parameter = null;
            if (p != null) {
                //add char ° as binopnode
                BinOpNode root = new BinOpNode("°", p, factor(null));
                term1Parameter = root;
            } else {
                term1Parameter = factor(null);
            }
            return term(term1Parameter);
        } else if (c == '|' || c == ')') {
            //epsilon
            return p;
        }

        throw new ExpressionNotValidException();
    }

    private Visitable re(Visitable p) throws ExpressionNotValidException {

        if (regEx.charAt(pos) == '|') {
            //add char | to tree as binopnode
            //|termRE
            pos++;
            BinOpNode root = new BinOpNode("|", p, term(null));
            return re(root);
        } else if (regEx.charAt(pos) == ')') {
            //epsilon
            pos++;
            return p;
        }

        throw new ExpressionNotValidException();
    }

    private Visitable factor(Visitable p) throws ExpressionNotValidException {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //elem hop
            return hop(elem(null));
        }

        throw new ExpressionNotValidException();
    }

    private Visitable hop(Visitable p) throws ExpressionNotValidException {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(' || c == '|' || c == ')') {
            //epsilon
            return p;
            //add Operator as UnaryOpNode (*,+,?) to tree
        } else if (c == '*') {
            //sternchen
            pos++;
            return (new UnaryOpNode("*", p));
        } else if (c == '+') {
            //plus
            pos++;
            return new UnaryOpNode("+", p);
        } else if (c == '?') {
            //?
            pos++;
            return new UnaryOpNode("?", p);
        }
        throw new ExpressionNotValidException();
    }

    private Visitable elem(Visitable p) throws ExpressionNotValidException {
        //add char to tree
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //alphanum
            return alphanum(null);
        } else if (c == '(') {
            //klammer regexp klammer
            pos++;
            return regExp(null);
        }
        throw new ExpressionNotValidException();
    }

    private Visitable alphanum(Visitable p) throws ExpressionNotValidException {
        //add operand node to tree
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //...
            pos++;
            return new OperandNode(c + "");
        }
        throw new ExpressionNotValidException();
    }

}