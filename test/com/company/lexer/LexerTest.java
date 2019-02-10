package com.company.lexer;

import com.company.base.DFAState;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class LexerTest {

    @Test
    public void match() {
        SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<DFAState, Map<Character, DFAState>>();
        Set<Integer> positions = new HashSet<Integer>();
        Map<Character, DFAState> transmissionMap = new TreeMap<>();

        positions.add(2);
        DFAState S1 = new DFAState(1, false, positions);
        DFAState S2 = new DFAState(2, false, positions);
        DFAState S3 = new DFAState(3, true, positions);
        DFAState S4 = new DFAState(4, false, positions);


        stateTransitionTable.put(S3, transmissionMap);

        transmissionMap = new TreeMap<>();
        transmissionMap.put('a', S2);
        stateTransitionTable.put(S1, transmissionMap);

        transmissionMap = new TreeMap<>();
        transmissionMap.put('b', S4);
        stateTransitionTable.put(S2, transmissionMap);

        transmissionMap = new TreeMap<>();
        transmissionMap.put('a', S3);
        stateTransitionTable.put(S4, transmissionMap);


        Lexer lexer = new Lexer(stateTransitionTable);
        Assert.assertTrue(lexer.match("ababbbaaa"));
        Assert.assertFalse(lexer.match("abbaabb"));

    }
}