# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 11:16:08 2014

@author: Dan
"""

def to_rna(DNA):
    D = []    
    for i in DNA:
        if i == 'G':
            D.append('C')
        elif i == 'C':
            D.append('G')
        elif i == 'T':
            D.append('A')
        elif i == 'A':
            D.append('U')
        else:
            return 'Not a DNA nucleotide'
    
    return ''.join(D)
        
