#!/usr/bin/env python

def distance(x, y):
    dist = 0
    for c1, c2 in zip(x, y):
        if not c1 == c2:
            dist += 1
    return dist
