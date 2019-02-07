package com.company.base;

import java.util.HashSet;
import java.util.Set;

class FollowposTableEntry
{
    public final int position;
    public final String symbol;
    public final Set<Integer> followpos = new HashSet<>();
    public FollowposTableEntry(int position, String symbol)
    {
        this.position = position;
        this.symbol = symbol;
    }
}
