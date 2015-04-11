# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 01:47:53 2014

@author: laegrim
"""

def to_rna(dna):
    '''
    Maps a DNA sequence to an RNA sequence
    '''
    
    def mapping(base):
        '''
        defines the base pair mapping
        '''
        
        if base == 'C':
            return 'G'
        elif base == 'G':
            return 'C'
        elif base == 'A':
            return 'U'
        elif base == 'T':
            return 'A'
            
    return ''.join(map(mapping, dna[:]))
