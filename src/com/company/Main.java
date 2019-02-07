package com.company;

import com.company.base.TestUtilities;
import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.visitor.first.VisitorFirst;

public class Main {

    public static void main(String[] args) {
        Visitable visitable = TestUtilities.createTestTree();
        Visitor visitorFirst = new VisitorFirst();
        visitorFirst.visit(visitable);
        System.out.println(visitable.isNullable());
        System.out.println(visitable.getFirstpos());
        System.out.println(visitable.getLastpos());
        System.out.println();
    }
}
