#!/usr/bin/python
def distance(dna1,dna2):
    return sum([a!=b for (a,b) in zip(dna1,dna2)])
