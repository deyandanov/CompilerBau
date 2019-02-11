package com.company;

import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.parser.ITopDownParser;
import com.company.parser.TopDownParser;
import com.company.visitor.first.VisitorFirst;
import com.company.visitor.second.FollowposTableEntry;
import com.company.visitor.second.VisitorSecond;

import java.util.Scanner;
import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {

        ITopDownParser parser = new TopDownParser();
        Visitor visitorFirst = new VisitorFirst();
        Visitor visitorSecond = new VisitorSecond();

        Visitable tree = null;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Geben Sie ihren regulären Ausdruck in der Form (RA)# ein:");

        String regEx = scanner.next();

        tree = parser.parse(regEx);

        if(tree == null) {
            System.out.println("Ihr Ausdruck konnte nicht geparst werden!");
            return;
        }

        tree.accept(visitorFirst);
        tree.accept(visitorSecond);

        SortedMap<Integer, FollowposTableEntry>  followposTableEntries =((VisitorSecond) visitorSecond).getFollowposTableEntries();

        //TODO: Ausführen des DEA-Erzeugers und des Lexers



        /*Visitable visitable = TestUtilities.createTestTree();
        Visitor visitorFirst = new VisitorFirst();
        visitorFirst.visit(visitable);
        System.out.println(visitable.isNullable());
        System.out.println(visitable.getFirstpos());
        System.out.println(visitable.getLastpos());
        System.out.println();
        Visitor visitorSecond = new VisitorSecond();
        visitorSecond.visit(visitable);



        SortedMap<Integer, FollowposTableEntry> map = ((VisitorSecond) visitorSecond).getFollowposTableEntries();
        for (int key : map.keySet()) {
            System.out.println(map.get(key).getFollowpos());
        }

        TopDownParser topDownParser = new TopDownParser();

        try {
            Visitable testtree =  topDownParser.parse("(a+b)#");
            System.out.println("E");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
