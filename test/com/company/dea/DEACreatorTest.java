package com.company.dea;

import com.company.base.DFAState;
import com.company.visitor.second.FollowposTableEntry;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DEACreatorTest {                                                                       //Lennart Schupp

    @Test
    public void createTable() {

        TreeMap<Integer, FollowposTableEntry> positionFollowPosMap = new TreeMap<>();                                           //Test Daten Erstellen
        positionFollowPosMap.put(1, new FollowposTableEntry(1, "a", new HashSet<>(Arrays.asList(1,2,3))));
        positionFollowPosMap.put(2, new FollowposTableEntry(2, "b", new HashSet<>(Arrays.asList(1,2,3))));
        positionFollowPosMap.put(3, new FollowposTableEntry(3, "#", new HashSet<>()));


        DEACreator builder = new DEACreator();

        SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = builder.createTable(positionFollowPosMap);       //Builder ausführen



        assertEquals(2, stateTransitionTable.keySet().size());                  //Ergebnis Prüfen
    }
}