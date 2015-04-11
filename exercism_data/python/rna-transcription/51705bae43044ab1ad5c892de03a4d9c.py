#!/usr/bin/env python

def to_rna(dna):
    trans = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

    return ''.join(trans.get(n, n) for n in dna)
