package com.company.dea;

import com.company.base.DFAState;
import com.company.visitor.second.FollowposTableEntry;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DEACreatorTest {

    @Test
    public void createTable() {

        TreeMap<Integer, FollowposTableEntry> positionFollowPosMap = new TreeMap<>();
        positionFollowPosMap.put(1, new FollowposTableEntry(1, "a"));
        positionFollowPosMap.put(2, new FollowposTableEntry(2, "a"));
        positionFollowPosMap.put(3, new FollowposTableEntry(3, "b"));
        positionFollowPosMap.put(4, new FollowposTableEntry(4, "a"));
        positionFollowPosMap.put(5, new FollowposTableEntry(5, "b"));
        positionFollowPosMap.put(6, new FollowposTableEntry(6, "#"));

        DEACreator creator = new DEACreator(positionFollowPosMap);

        SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = creator.createTable();


        assertEquals(4, stateTransitionTable.keySet().size());
    }
}