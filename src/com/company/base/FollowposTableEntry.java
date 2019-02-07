package com.company.base;

import java.util.HashSet;
import java.util.Set;

class FollowposTableEntry {
    private final int position;
    private final String symbol;
    private final Set<Integer> followpos = new HashSet<>();



    public int getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }

    public Set<Integer> getFollowpos() {
        return followpos;
    }



    public FollowposTableEntry(int position, String symbol) {
        this.position = position;
        this.symbol = symbol;
    }
}
