#!/usr/bin/env python

def to_rna(dna):
    rna = ''
    
    for nucleotide in dna:
        if nucleotide == 'A':
            rna += 'U'
        elif nucleotide == 'C':
            rna += 'G'
        elif nucleotide == 'G':
            rna += 'C'
        else:
            rna += 'A'

    return rna
