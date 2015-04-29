#!/usr/bin/env python3
""" module dna for exercism.io programming training"""

TRANSLATOR = {'C': 'G', 'G': 'C', 'A': 'U', 'T': 'A'}

def to_rna(in_string):
    """ function to_rna translates dna bases to rna bases"""
    translated_list = [TRANSLATOR.get(x) for x in list(in_string)]
    out_string = ''.join(translated_list) # convert the list to a string
    return out_string
    
