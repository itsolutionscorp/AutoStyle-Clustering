#!/usr/bin/env python

def to_rna(dna):
    dna_to_rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
    rna = ''.join([dna_to_rna[c.upper()] for c in dna])
    return rna
