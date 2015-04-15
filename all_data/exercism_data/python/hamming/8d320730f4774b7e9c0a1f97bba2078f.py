# -*- coding: utf-8 -*-
"""
Created on Mon Oct  6 16:05:27 2014

@author: luke
"""

def distance(strandOne, strandTwo):
    dist=0
    for x in range(len(strandOne)):
        if strandOne[x] != strandTwo[x]:
            dist += 1
    return dist
