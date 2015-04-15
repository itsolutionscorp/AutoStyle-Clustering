#!/usr/bin/env python

dna_to_rna = { 'G' : 'C', 'C' : 'G',
               'T' : 'A', 'A' : 'U'}

def to_rna(string):
    result = ""
    for char in string:
        result += dna_to_rna[char]
    return result
