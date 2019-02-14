package com.company.dea;

import com.company.base.DFAState;
import com.company.visitor.second.FollowposTableEntry;
import com.company.visitor.second.VisitorSecond;

import java.util.*;

public class DEACreator {

    private SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<DFAState, Map<Character, DFAState>>();
    private VisitorSecond visitorSecond = new VisitorSecond();
    private SortedMap<Integer, FollowposTableEntry> followposTableEntries;

    public DEACreator(SortedMap<Integer, FollowposTableEntry> followposTableEntries) {
        this.followposTableEntries = followposTableEntries;
    }

    public SortedMap<DFAState, Map<Character, DFAState>> createTable(){

        //FollowposTableEntry root = followposTableEntries.firstKey().getValue();
        FollowposTableEntry root = followposTableEntries.get(followposTableEntries.firstKey());
        //firstPos anwenden um T0 zu bekommen und zu stateTransitionTable hinzufügen
        //test
        DFAState firstState = new DFAState(false, new HashSet<Integer>(Arrays.asList(1, 2, 3)));
        stateTransitionTable.put(firstState, null);

        //woher firstPos?


        ArrayList<DFAState> States = new ArrayList<>();
        States.add(firstState);

        while((States = checkStates()).size() != 0) for (DFAState newState : States) {
            Map<Character, DFAState> symbolToState = new HashMap<>();
            for (Integer pos : newState.positionsSet) {
                int i = pos;
                FollowposTableEntry relatedEntry = followposTableEntries.get(i);
                char symbol = relatedEntry.getSymbol().charAt(0);
                if (symbol == '#') continue;
                if (null != symbolToState.get(symbol))
                    symbolToState.get(symbol).positionsSet.addAll(relatedEntry.getFollowpos());
                else {
                    HashSet<Integer> positionSet = new HashSet<>(relatedEntry.getFollowpos());
                    symbolToState.put(symbol, new DFAState(false, positionSet));
                }
            }


            if (!symbolToState.keySet().isEmpty()) {
                Map<Character, DFAState> transitionMap = new HashMap<>();
                for (char symbol : symbolToState.keySet()) {
                    DFAState state = symbolToState.get(symbol);

                    DFAState stateInTable;
                    if ((stateInTable = stateFromTable(state)) == null) {
                        stateTransitionTable.put(state, null);
                        transitionMap.put(symbol, state);
                    } else {
                        transitionMap.put(symbol, stateInTable);
                    }
                }
                stateTransitionTable.put(newState, transitionMap);
            } else {
                stateTransitionTable.put(newState, new HashMap<>());
            }


        }
        int posOfHashTag = -1;
        for(FollowposTableEntry entry: followposTableEntries.values()){
            if(entry.getSymbol().equals("#")){
                posOfHashTag = entry.getPosition();
                break;
            }
        }
        if(posOfHashTag == -1){
            throw new RuntimeException("# not found");
        }

        for(DFAState state: stateTransitionTable.keySet()){
            if(state.positionsSet.contains(posOfHashTag)){
                state.isAcceptingState = true;
            }
        }
        return stateTransitionTable;
    }



    private DFAState stateFromTable(DFAState state) {
        for(DFAState key: stateTransitionTable.keySet()){
            if(state.positionsSet.equals(key.positionsSet)){
                return key;
            }
        }
        return null;
    }

    private ArrayList<DFAState> checkStates(){
        ArrayList<DFAState> newStates = new ArrayList<>();
        for(DFAState state: stateTransitionTable.keySet()){
            if(stateTransitionTable.get(state) == null){
                newStates.add(state);
            }
        }
        return newStates;
    }

}

