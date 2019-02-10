package com.company.visitor.first;

import com.company.base.*;
import com.company.base.BinOpNode;
import com.company.base.UnaryOpNode;
import com.company.base.testres.TestUtilities;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class VisitorFirstTest {
    //Teil von Noah Börger

    @Test
    public void testNullableWithNotNullableNode() {
        BinOpNode testTree = (BinOpNode) createVisitedTestTree();
        Assert.assertFalse(testTree.isNullable());
    }

    @Test
    public void testNullableWithNullableNode() {
        BinOpNode testTree = (BinOpNode) createVisitedTestTree();
        UnaryOpNode nullableNode = (UnaryOpNode) ((BinOpNode) ((BinOpNode) ((BinOpNode) testTree.getLeft()).getLeft()).getLeft()).getLeft();
        boolean nullableNodeNullable = nullableNode.isNullable();
        Assert.assertTrue(nullableNodeNullable);
    }

    @Test
    public void testFirstPos() {
        Visitable testTree = createVisitedTestTree();
        Set<Integer> firstPos = testTree.getFirstpos();
        if (firstPos.size() == 3 && firstPos.contains(1) && firstPos.contains(2) && firstPos.contains(3)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    @Test
    public void testLastPos() {
        Visitable testTree = createVisitedTestTree();
        Set<Integer> lastPos = testTree.getLastpos();
        if (lastPos.size() == 1 && lastPos.contains(6)) {
            Assert.assertTrue(true);
        } else {
            Assert.fail();
        }
    }

    private Visitable createVisitedTestTree() {
        Visitable visitable = TestUtilities.createTestTree();
        Visitor visitorFirst = new VisitorFirst();
        visitable.accept(visitorFirst);
        return visitable;
    }
}