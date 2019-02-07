package com.company;

import com.company.base.TestUtilities;
import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.visitor.first.VisitorFirst;
import com.company.visitor.second.FollowposTableEntry;
import com.company.visitor.second.VisitorSecond;

import java.util.SortedMap;

public class Main {

    public static void main(String[] args) {
        Visitable visitable = TestUtilities.createTestTree();
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
    }
}
