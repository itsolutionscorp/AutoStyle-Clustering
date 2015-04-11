# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 22:51:32 2014

@author: johann
"""
from itertools import izip_longest
def hamming(m1,m2):
    return sum(ch1 != ch2 for ch1,ch2 in izip_longest(m1,m2,fillvalue=' '))    
