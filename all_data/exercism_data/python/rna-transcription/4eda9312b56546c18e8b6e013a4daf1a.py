#!/usr/bin/python
import string

def to_rna(dna):
    trans_table = string.maketrans('GCTA', 'CGAU')
    return string.translate(dna, trans_table)
    
