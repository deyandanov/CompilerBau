package com.company.integration;

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
import org.junit.Assert;
import org.junit.Test;

import java.util.SortedMap;

public class IntegrationsTest {


    @Test
    public void IntegrationTest() {
        ITopDownParser parser = new TopDownParser();
        Visitor visitorFirst = new VisitorFirst();
        VisitorSecond visitorSecond = new VisitorSecond();
        DEACreator deaCreator = new DEACreator();

        Visitable visitable = null;
        try {
            visitable = parser.parse("((a|b)*aab)#");
        } catch (ExpressionNotValidException e) {
            Assert.fail();
        }
        if (visitable == null) {
            Assert.fail();
        }

        visitable.accept(visitorFirst);
        visitable.accept(visitorSecond);

        SortedMap<Integer, FollowposTableEntry> followposTableEntries = visitorSecond.getFollowposTableEntries();


        Lexer lexer = new Lexer(deaCreator.createTable(followposTableEntries));

        Assert.assertTrue(lexer.match("aababbbaabaab"));
    }
}
