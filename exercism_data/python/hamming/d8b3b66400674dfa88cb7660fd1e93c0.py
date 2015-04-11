#!/usr/bin/python
def distance(dna1,dna2):
    n=len(dna1)
    if n==len(dna2):
        return sum([dna1[i]!=dna2[i] for i in range(n)])
    else:
        return "dna strings not equal length"
