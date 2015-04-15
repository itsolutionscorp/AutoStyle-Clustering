# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 16:09:03 2014

@author: Blair
"""
import string

def to_rna(DNA):
    rnaTable = string.maketrans("GCTA", "CGAU")
    return DNA.upper().translate(rnaTable)
