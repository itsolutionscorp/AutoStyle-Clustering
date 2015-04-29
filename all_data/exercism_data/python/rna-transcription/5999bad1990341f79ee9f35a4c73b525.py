#!/usr/bin/py

def to_rna(dna):
    rna = ''
    complements = {'A':'U', 'T':'A', 'C':'G', 'G':'C'}
    for tide in dna:
        rna += complements[tide]
    return rna
