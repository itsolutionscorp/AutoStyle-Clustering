#!/usr/bin/env python

from operator import ne

def hamming(s,t):
    return sum(map(ne,s,t))
