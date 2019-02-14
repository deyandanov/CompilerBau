package com.company.integration;

import com.company.base.Visitable;
import com.company.base.Visitor;
import com.company.base.exp.ExpressionNotValidException;
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
    public void IntegrationsTest() {
        ITopDownParser parser = new TopDownParser();
        Visitor visitorFirst = new VisitorFirst();
        Visitor visitorSecond = new VisitorSecond();

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
        SortedMap<Integer, FollowposTableEntry> followposTableEntryMap = ((VisitorSecond) visitorSecond).getFollowposTableEntries();
        //Todo: weitergeben an DEA Erzeuger und Lexer um vollständige FKT zu testen, sobald der DEA Erzeuger geschrieben wurde
    }
}
