#!/usr/bin/python
#############################################
#
# File Name : dna.py
#
# Last Modified : Mon 27 Oct 2014 10:30:46 PM PDT
#
#############################################
from string import maketrans

def to_rna(sequence):
    dna = "GCTA"
    rna = "CGAU"
    
    rna_translation = maketrans(dna,rna)
    rna_sequence = sequence.translate(rna_translation)

    #print rna_sequence
    return rna_sequence
        
    
