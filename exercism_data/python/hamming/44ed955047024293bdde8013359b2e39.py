# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 11:34:33 2014

@author: Dan
"""

def hamming(DNA1,DNA2):
    stri = []
    for i in DNA1:
        stri.append(i)
    stri2 = []
    for j in DNA2:
        stri2.append(j)
    
    count = 0
    for i, j in zip(stri, stri2):
        if i != j:
            count += 1
    
    count = count + abs(len(stri) - len(stri2))
    
    return count
    
    
