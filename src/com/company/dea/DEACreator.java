package com.company.dea;
import com.company.base.DFAState;
import com.company.visitor.second.FollowposTableEntry;

import java.util.*;
public class DEACreator {                       //Lennart Schupp

    private int id;

    public DEACreator() {
    }


    public SortedMap<DFAState, Map<Character, DFAState>> createDFAStateMap(SortedMap<Integer, FollowposTableEntry> followPosTableEntries){

            SortedMap<Integer, FollowposTableEntry> followPosTableEntries1 = followPosTableEntries;
            ArrayList<DFAState> states = new ArrayList<>();
            SortedMap<DFAState, Map<Character, DFAState>> stateTransitionTable = new TreeMap<>();
            ArrayList<Integer> unmarkedPos=new ArrayList<>();
            ArrayList<String> alphabetArray=new ArrayList<>();
            int firstpos=0;
            int arrayFirstpos=0;

        {
            int i=1;
            while (i<=followPosTableEntries.size()) {                   //Alphabet und Firstposition ermitteln
                boolean exists=false;
                if(alphabetArray.size()>0) {
                    for (String symbol : alphabetArray) {
                        if (followPosTableEntries.get(i).getSymbol().equals(symbol)) {
                            exists = true;
                            break;
                        }
                    }
                }
                else{
                    firstpos=followPosTableEntries.get(i).position;
                    arrayFirstpos=i;
                }
                if (exists) {
                } else {
                    alphabetArray.add(followPosTableEntries.get(i).symbol);
                    if(firstpos>followPosTableEntries.get(i).position){
                        firstpos=followPosTableEntries.get(i).position;
                        arrayFirstpos=i;
                    }
                }
                i++;
            }
        }

        int unmarkedStates=0;
        ArrayList<Integer> startList = new ArrayList<>(Collections.singletonList(arrayFirstpos));
        boolean isAccepting = false;
        Set<Integer> followPos= new HashSet<>();

        for (int i = 0; i < startList.size(); i++) {
            int thePos = startList.get(i);
            followPos.addAll(followPosTableEntries.get(thePos).followpos);
            if (followPosTableEntries.get(thePos).symbol.equals("#")) isAccepting = true;
        }

            DFAState dfaState = new DFAState(id++, isAccepting, followPos);
            states.add(dfaState);
            unmarkedStates += 1;
            unmarkedPos.add(states.size()-1);

            while(!(unmarkedStates == 0)){

                Map<Character,DFAState> map=new TreeMap<>();
                int markedStatePos=unmarkedPos.get(0);
                unmarkedStates -= 1;
                unmarkedPos.remove(0);

                for(String symbol: alphabetArray) {

                    ArrayList<Integer> list = new ArrayList<>();
                    int countList = 0;
                    Set<Integer> comparePos=new HashSet<>();

                    for (int pos : states.get(markedStatePos).positionsSet) {
                        if(symbol.equals(followPosTableEntries.get(pos).symbol)){
                            list.add(pos);
                            countList+=followPosTableEntries.get(pos).followpos.size();
                            comparePos.addAll(followPosTableEntries.get(pos).followpos);
                        }
                    }

                    boolean b=false;                    //Platzhalter zur prüfung ob bereits existiert
                    DFAState newState=null;

                    for(DFAState state:states){
                        if(state.positionsSet.size()==countList){
                            if(state.positionsSet.equals(comparePos)){
                                b=true;
                                newState=state;
                            }
                        }
                    }
                    if(!b){

                        isAccepting = false;
                        followPos= new HashSet<>();
                        int i = 0;

                        while (i < list.size()) {
                            int thePos = list.get(i);
                            followPos.addAll(followPosTableEntries.get(thePos).followpos);
                            if (followPosTableEntries.get(thePos).symbol.equals("#")) isAccepting = true;
                            i++;
                        }

                        dfaState = new DFAState(id++, isAccepting, followPos);
                        newState=dfaState;
                        states.add(newState);
                        unmarkedStates++;
                        unmarkedPos.add(states.size()-1);
                    }
                    map.put(symbol.charAt(0),newState);
                }
                stateTransitionTable.put(states.get(markedStatePos),map);
            }
            return stateTransitionTable;
        }

    }


