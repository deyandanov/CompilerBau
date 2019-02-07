package com.company.visitor.first;

import com.company.DFAState;
import com.company.Lexer;
import com.company.base.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class VisitorFirstTest {

    @Test
    public void testNullable() {
        BinOpNode testTree = (BinOpNode) TestUtilities.createTestTree();
        Assert.assertFalse(testTree.isNullable());
    }

    @Test
    public void testFirstPos() {
    }

    @Test
    public void testLastPos() {
    }

}