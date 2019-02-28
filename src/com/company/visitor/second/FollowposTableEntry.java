package com.company.visitor.second;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FollowposTableEntry {
    //Implementiert von Eric Gendner


    private final int position;
    private final String symbol;
    private Set<Integer> followpos = new HashSet<>();

    public FollowposTableEntry(int position, String symbol, Set<Integer> followpos) {
        this.position = position;
        this.symbol = symbol;
        this.followpos = followpos;
    }

    public FollowposTableEntry(int position, String symbol) {
        this.position = position;
        this.symbol = symbol;
        followpos = new HashSet<>();
    }

    public int getPosition() {
        return position;
    }

    public String getSymbol() {
        return symbol;
    }

    public Set<Integer> getFollowpos() {
        return followpos;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FollowposTableEntry) {
            FollowposTableEntry compareObject = (FollowposTableEntry) object;
            if (position == compareObject.position && symbol.equals(compareObject.symbol) && followpos.containsAll(compareObject.followpos)) {
                return true;
            }
        }
        return false;
    }
}
