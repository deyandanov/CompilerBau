package com.company.visitor.second;


import com.company.base.*;
import com.company.base.testres.TestUtilities;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;


public class VisitorSecondTest {
//Implementiert von Eric Gendner

    @Test
    public void secondVisitorTest() {
        Visitable visitable = TestUtilities.createTestTreeFolllowPos();     //calls Methode which generates tree with firstpos and lastpos in elements
        Visitor visitorSecond = new VisitorSecond();
        visitable.accept(visitorSecond);
        SortedMap<Integer, FollowposTableEntry> mapFollowpos = ((VisitorSecond) visitorSecond).getFollowposTableEntries();

        if (mapFollowpos.size() != 6) {
            Assert.fail();
            return;
        }

        FollowposTableEntry f1 = new FollowposTableEntry(1, "a");
        f1.getFollowpos().add(1);
        f1.getFollowpos().add(2);
        f1.getFollowpos().add(3);

        FollowposTableEntry f2 = new FollowposTableEntry(2, "b");
        f2.getFollowpos().add(1);
        f2.getFollowpos().add(2);
        f2.getFollowpos().add(3);

        FollowposTableEntry f3 = new FollowposTableEntry(3, "a");
        f3.getFollowpos().add(4);

        FollowposTableEntry f4 = new FollowposTableEntry(4, "b");
        f4.getFollowpos().add(5);

        FollowposTableEntry f5 = new FollowposTableEntry(5, "b");
        f5.getFollowpos().add(6);

        FollowposTableEntry f6 = new FollowposTableEntry(6, "#");


        Assert.assertTrue(mapFollowpos.containsValue(f1));
        Assert.assertTrue(mapFollowpos.containsValue(f2));
        Assert.assertTrue(mapFollowpos.containsValue(f3));
        Assert.assertTrue(mapFollowpos.containsValue(f4));
        Assert.assertTrue(mapFollowpos.containsValue(f5));
        Assert.assertTrue(mapFollowpos.containsValue(f6));



    }
}
