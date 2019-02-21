package com.company;

import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.base.exp.ExpressionNotValidException;
import com.company.dea.DEACreator;
import com.company.lexer.Lexer;
import com.company.parser.ITopDownParser;
import com.company.parser.TopDownParser;
import com.company.visitor.first.VisitorFirst;
import com.company.visitor.second.FollowposTableEntry;
import com.company.visitor.second.VisitorSecond;

import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {

        //Main zum Testen, spiegelt lediglich den Integrationstest wieder

        ITopDownParser parser = new TopDownParser();
        Visitor visitorFirst = new VisitorFirst();
        Visitor visitorSecond = new VisitorSecond();

        Visitable tree = null;

        String regEx = null;

        Scanner scanner = new Scanner(System.in);

        boolean expressionParsed = false;

        while (!expressionParsed) {

            System.out.println("Geben Sie ihren regulären Ausdruck in der Form (r)# ein:");

            regEx = scanner.next();

            try {
                tree = parser.parse(regEx);
            } catch (ExpressionNotValidException e) {
                System.out.println("Ihr Ausdruck konnte nicht geparst werden!");
                if (tree != null) {
                    e.printStackTrace();
                    return;
                }
            }

            if (tree != null) {
                expressionParsed = true;
            }
        }
        tree.accept(visitorFirst);
        tree.accept(visitorSecond);

        SortedMap<Integer, FollowposTableEntry> followposTableEntries = ((VisitorSecond) visitorSecond).getFollowposTableEntries();

        DEACreator deaCreator = new DEACreator();

        Lexer lexer = new Lexer(deaCreator.createTable(followposTableEntries));

        lexer.match(regEx);
    }
}
