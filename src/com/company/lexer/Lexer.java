package com.company.lexer;

import com.company.base.DFAState;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Lexer {                                                                                    //Created by 2560035

    private SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<DFAState, Map<Character, DFAState>>();
    public Lexer(SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable){
        this.stateTransitionTable = stateTransitionTable;
    }
    public boolean match(String string){

        char[] charArray = string.toCharArray();
        DFAState state = stateTransitionTable.firstKey();

        for (char symbol:charArray) {
            char letter = symbol;

            if (state.isAcceptingState== true){
                return true;
            }
            if (stateTransitionTable.get(state).containsKey(letter)){
                state = stateTransitionTable.get(state).get(letter);
            }
            else return false;
        }
        return false;
    }
}
