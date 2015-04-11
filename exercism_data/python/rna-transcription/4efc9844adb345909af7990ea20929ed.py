# -*- coding: utf-8 -*-
"""
Created on Thu Oct 09 10:08:54 2014

@author: klin
"""
import string

def to_rna(s): 
    trans = string.maketrans("GCTA", "CGAU")
    return s.translate(trans)
    
print to_rna("ABC")
