package com.company.dea;

import com.company.base.DFAState;
import com.company.visitor.second.FollowposTableEntry;
import com.company.visitor.second.VisitorSecond;

import java.util.*;



public class DEACreator {                                                                                               //Lennart Schupp

    private SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<>();
    private VisitorSecond visitorSecond = new VisitorSecond();
    private SortedMap<Integer, FollowposTableEntry> followposTableEntries;

    public SortedMap<DFAState, Map<Character, DFAState>> createTable(SortedMap<Integer, FollowposTableEntry> followPosTableEntries) {

        ArrayList<DFAState> states = new ArrayList<DFAState>();

        //Generate alle States

        for (int i = 0; i < followPosTableEntries.size(); i++) {
            boolean accsepting = false;
            if (Objects.equals(followPosTableEntries.get(i), "#")) accsepting = true;
            states.add(new DFAState(followPosTableEntries.get(i).getPosition(),                     //can't get data from followPosTableEntries, but why?
                    accsepting,
                    followPosTableEntries.get(i).getFollowpos()));
        }


        //Building the Transition Table

        SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<>();

        for (DFAState state : states) {
            Map<Character, DFAState> map = new TreeMap<>();
            Object[] theFollowPositions = state.positionsSet.toArray();
            for (Object theFollowPosition : theFollowPositions) {
                for (int j = 0; j < followPosTableEntries.size(); j++)
                    if (followPosTableEntries.get(j).getPosition() == (int) theFollowPosition) {
                        String theString = followPosTableEntries.get(j).getSymbol();
                        map.put(theString.charAt(0), states.get(j));
                    }
            }
            stateTransitionTable.put(state, map);
        }

        return stateTransitionTable;
    }


}

