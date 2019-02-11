package com.company.visitor.second;

import java.util.HashSet;
import java.util.Set;

public class FollowposTableEntry {
    private final int position;
    private final String symbol;
    private final Set<Integer> followpos = new HashSet<>();




    public int getPosition() {
        return position;
    }       //do wen need this ?

    public String getSymbol() {
        return symbol;
    }       //do we need this too ?

    public Set<Integer> getFollowpos() {
        return followpos;
    }

    public FollowposTableEntry(int position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }
}
