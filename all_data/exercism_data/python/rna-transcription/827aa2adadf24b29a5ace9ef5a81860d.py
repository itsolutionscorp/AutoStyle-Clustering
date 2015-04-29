#!/usr/bin/env python3
""" module dna for exercism.io programming training"""

TRANSLATOR = {'C': 'G', 'G': 'C', 'A': 'U', 'T': 'A'}

def to_rna(in_string):
    """ function to_rna translates dna bases to rna bases"""
    return ''.join(TRANSLATOR[x] for x in in_string)
    
