package com.company.visitor.second;

import java.util.HashSet;
import java.util.Set;

public class FollowposTableEntry {
    private final int position;
    private final String symbol;
    private final Set<Integer> followpos = new HashSet<>();


    TableTreeMap tableTreeMap=new TableTreeMap();


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
