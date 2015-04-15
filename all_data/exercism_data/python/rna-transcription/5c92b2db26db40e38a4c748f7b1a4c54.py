'''
Created on Oct 2, 2014

@author: dulshani
'''

dna_to_rna = {"G":"C", "C":"G", "T":"A", "A":"U"}

def to_rna(dna):
    rna =""
    for ch in dna:
        rna += dna_to_rna[ch]
    return rna
