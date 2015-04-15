#!/usr/bin/python
#############################################
#
# File Name : dna.py
#
# Last Modified : Mon 27 Oct 2014 10:16:58 PM PDT
#
#############################################

def to_rna(dna):
    
    rna_sequence = ""
    
    for letter in dna:
        #print letter, 
        if letter == 'G':
            rna_trans = 'C'
        elif letter == 'C':
            rna_trans = 'G'
        elif letter == 'T':
            rna_trans = 'A'
        elif letter == 'A':
            rna_trans = 'U'
        rna_sequence += rna_trans
    
    #print rna_sequence
    return rna_sequence
        
    
