# -*- coding: utf-8 -*-
"""
Created on Mon Oct  6 15:40:48 2014

@author: luke
"""

def to_rna(nuc):
    
    reverse = ""
    for x in nuc:
        if x == 'G':
            reverse += 'C'
        elif x == 'C':
            reverse += 'G'
        elif x == 'T':
            reverse += 'A'
        else:
            reverse += 'U'
    return reverse
        
            
