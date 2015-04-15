# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 22:51:32 2014

@author: johann
"""

def hamming(m1,m2):
    a = abs(len(m1) - len(m2))
    return a + sum(ch1 != ch2 for ch1,ch2 in zip(m1,m2))    
