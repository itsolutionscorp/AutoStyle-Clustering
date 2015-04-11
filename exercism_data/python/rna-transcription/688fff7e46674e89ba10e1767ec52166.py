# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 12:31:59 2014
"""

def rna_transcribe(char):
     if char == 'G':
         return_char = 'C'
     elif char == 'C':
         return_char = 'G'
     elif char == 'T':
         return_char = 'A'
     elif char == 'A':
         return_char = 'U'

     return return_char

def to_rna(input_dna):

    return_list = []

    if len(input_dna) == 1:
        return_list.append(rna_transcribe(input_dna))
    else:
        for m in input_dna:
            return_list.append(rna_transcribe(m))

    return ''.join(return_list)


