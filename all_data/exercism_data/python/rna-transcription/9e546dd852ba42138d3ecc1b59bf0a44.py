# -*- coding: utf-8 -*-
"""
Created on Fri Sep 26 15:56:58 2014

@author: rebuhr
"""

# program that, given a DNA strand, returns its RNA complement (per RNA transcription)

def to_rna(sequence):
    rna = []    
    for nucleotide in sequence:
        if nucleotide == 'A':
            rna += 'U'
        if nucleotide == 'C':
            rna += 'G'
        if nucleotide == 'G':
            rna += 'C'
        if nucleotide == 'T':
            rna += 'A'
    rna = ''.join(rna)
    return rna
