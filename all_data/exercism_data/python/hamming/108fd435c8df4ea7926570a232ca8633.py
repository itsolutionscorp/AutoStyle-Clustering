#!/usr/bin/env python3


def hamming(base, change):
    total = 0
    for i, char in enumerate(base):
        try:
            changechar = change[i]
        except IndexError:
            total += 1
        else:
            if changechar != char:
                total += 1
    changelen = len(change)
    baselen = len(base)
    if changelen > baselen:
        total += (changelen - baselen)
    return total
