# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:28:06 2014

@author: Blair
"""
import string

def hamming(strandA, strandB):
    i = 0
    hammingDistance = 0
    #make sure both strands are the same length. Add trailing spaces to make them equal if necessary
    strandA = string.ljust(strandA, len(strandB))
    strandB = string.ljust(strandB, len(strandA))
    while i < len(strandA):
        if strandA[i] != strandB[i]:
            hammingDistance += 1
        i += 1
    return hammingDistance
