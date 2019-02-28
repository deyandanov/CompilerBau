package com.company.lexer;

import com.company.base.DFAState;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Lexer {                                                                                    //Created by Deyan Danov

    private SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<DFAState, Map<Character, DFAState>>();
    public Lexer(SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable){
        this.stateTransitionTable = stateTransitionTable;
    }
    public boolean match(String string){

        char[] charArray = string.toCharArray();                      // transform string to char-array
        DFAState state = stateTransitionTable.firstKey();             // setting DEAs state to its start state

        for (char symbol:charArray) {                                 // iterating through the whole char-array
            char letter = symbol;                                     // setting the letter to the current character from the char-array

            if (stateTransitionTable.get(state).containsKey(letter)){ // if next state exist, depending on state and letter
                state = stateTransitionTable.get(state).get(letter); // state gets set to the reference of the next state
            }
            else return false;                                       // if no next state exist for the given state and letter
                                                                     // the string does not match the DEA


        }
        if (state.isAcceptingState == true){                          // if the final state is an accepting state
            return true;                                              // the string matches the DEA
        }
        else return false;                                            //otherwise the string does not match the DEA
    }
}
