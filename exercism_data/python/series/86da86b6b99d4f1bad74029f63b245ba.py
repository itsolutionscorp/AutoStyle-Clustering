# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 17:37:42 2014

@author: johann
"""

def slices(number,slicelength):
    if slicelength <= 0 or len(number) < slicelength:
        raise ValueError
    slices = []
    for i in range(len(number) - slicelength + 1):
        slices.append(map(int,(number[i:i+slicelength])))
    return slices
