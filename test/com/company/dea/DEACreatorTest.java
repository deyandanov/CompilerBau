package com.company.dea;

import com.company.base.DFAState;
import com.company.visitor.second.FollowposTableEntry;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DEACreatorTest {                                                                       //Lennart Schupp

    @Test
    public void createTable() {

        TreeMap<Integer, FollowposTableEntry> positionFollowPosMap = new TreeMap<>();
        positionFollowPosMap.put(1, new FollowposTableEntry(1, "a", new HashSet<>(Arrays.asList(1,2,3))));
        positionFollowPosMap.put(2, new FollowposTableEntry(2, "b", new HashSet<>(Arrays.asList(1,2,3))));
        positionFollowPosMap.put(6, new FollowposTableEntry(3, "#", new HashSet<>()));

        DEACreator creator = new DEACreator();

        SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = creator.createTable(positionFollowPosMap);


        assertEquals(2, stateTransitionTable.keySet().size());
    }
}