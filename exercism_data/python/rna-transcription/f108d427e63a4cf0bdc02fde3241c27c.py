#!/usr/bin/env python
dna_to_rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}

def to_rna(dna):
    rna = ''.join([dna_to_rna[c.upper()] for c in dna])
    return rna
