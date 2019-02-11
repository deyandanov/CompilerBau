package com.company.parser;

import com.company.base.BinOpNode;
import com.company.base.OperandNode;
import com.company.base.UnaryOpNode;
import com.company.base.Visitable;


public class TopDownParser implements ITopDownParser {

    private String regEx;
    private int pos;

    @Override
    public Visitable parse(String regEx) {
        this.regEx = regEx;
        return start();
    }

    private Visitable start() {
        if (regEx.charAt(0) == '#') {
            //#
            return new OperandNode("#");
        } else if (regEx.charAt(0) == '(') {
            //(regexp)#
            OperandNode leaf = new OperandNode("#");
            pos++;
            BinOpNode root = new BinOpNode("°", regExp(null), leaf);
            return root;
        }
        return null;
    }

    private Visitable regExp(Visitable p) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //termRE
            return (re(term(null)));
        }

        return null;
    }

    private Visitable term(Visitable p) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //factor term
            Visitable term1Parameter = null;
            if (p != null) {
                pos++;
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

        return null;
    }

    private Visitable re(Visitable p) {

        if (regEx.charAt(pos) == '|') {
            //|termRE
            pos++;
            BinOpNode root = new BinOpNode("|", p, term(null));
            return re(root);
        } else if (regEx.charAt(pos) == ')') {
            //epsilon
            return p;
        }

        return null;
    }

    private Visitable factor(Visitable p) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z' || c == '(') {
            //elem hop
            return hop(elem(null));
        }

        return null;
    }

    private Visitable hop(Visitable p) {
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
        return null;
    }

    private Visitable elem(Visitable p) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //alphanum
            return alphanum(null);
        } else if (c == '(') {
            //klammer regexp klammer
            return regExp(null);
        }
        return null;
    }

    private Visitable alphanum(Visitable p) {
        char c = regEx.charAt(pos);

        if (c >= '0' && c <= '9' || c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z') {
            //...
            pos++;
            return new OperandNode(c + "");
        }
        return null;
    }

}