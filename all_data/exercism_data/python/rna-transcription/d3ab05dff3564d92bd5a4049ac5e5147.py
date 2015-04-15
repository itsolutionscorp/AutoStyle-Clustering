# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 22:31:39 2014

@author: johann
"""
from string import maketrans
def to_rna(message):
    trantab = maketrans("GCTA","CGAU")
    return message.translate(trantab)
