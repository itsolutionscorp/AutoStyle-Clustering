# -*- coding: utf-8 -*-

def to_rna(strand):
    d2r_map = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
    try:
        return ''.join(d2r_map[nuc] for nuc in strand)
    except KeyError:
        print("Unknown nucleotide in the DNA strand")
        
