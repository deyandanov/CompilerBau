package com.company.parser;

import com.company.base.BinOpNode;
import com.company.base.OperandNode;
import com.company.base.UnaryOpNode;
import com.company.base.Visitable;
import com.company.base.exp.ExpressionNotValidException;

//Programmiert von 5965012


public class TopDownParser implements ITopDownParser {

    private String regEx;
    private int pos;

    @Override
    public Visitable parse(String regEx) throws ExpressionNotValidException {
        this.regEx = regEx;
        return start();
}

    private Visitable start() throws ExpressionNotValidException {
        if (regEx.charAt(0) == '#') {
            //#
            return new OperandNode("#");
        } else if (regEx.charAt(0) == '(') {
            //(regexp)#
            pos++;
            OperandNode leaf = new OperandNode("#");
            BinOpNode root = new BinOpNode("°", regExp(null), leaf);
            return root;
        }
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
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //...
            pos++;
            return new OperandNode(c + "");
        }
        throw new ExpressionNotValidException();
    }

}