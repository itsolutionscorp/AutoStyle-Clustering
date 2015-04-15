# -*- coding: utf-8 -*-
"""
Created on Sun Oct 12 22:42:00 2014

@author: Home Base
"""

def to_rna(dna):
    convert={'G':'C','C':'G','T':'A','A':'U'}
    dnaList=list(dna)
    rna=[convert[nucleotide] for nucleotide in dnaList]
    return ''.join(rna)
