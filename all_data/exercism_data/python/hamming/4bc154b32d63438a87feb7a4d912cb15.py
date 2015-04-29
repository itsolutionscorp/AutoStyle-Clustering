#!/usr/bin/env python

def distance(strand, other):
    result = 0
    longer = max(len(strand), len(other))
    for i in range(longer):
        if strand[i] != other[i]:
            result += 1
    return result
