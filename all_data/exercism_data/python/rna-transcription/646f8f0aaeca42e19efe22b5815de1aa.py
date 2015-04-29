# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 20:46:56 2014

@author: luis
"""
from string import maketrans 

def to_rna(sequence):
    return sequence.translate(maketrans('ATCG','UAGC'))
    
