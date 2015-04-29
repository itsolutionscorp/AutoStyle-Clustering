# -*- coding: utf-8 -*-

def to_rna(x):
    x_s = list(x)
    rna = ''
    for c in x_s:
        if c == 'G':
            rna += 'C'
        elif c == 'C':
            rna += 'G'
        elif c == 'T':
            rna += 'A'
        elif c == 'A':
            rna += 'U'
    return rna
    
