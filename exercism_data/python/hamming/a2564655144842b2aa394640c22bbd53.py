# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 02:27:33 2014

@author: laegrim
"""

def hamming(strand1, strand2):
    '''
    Find the hamming length between two DNA strands
    '''
    #Iterate over the length of the smaller strand, adding up differences
    shorter = min(len(strand1), len(strand2))
    longer = max(len(strand1), len(strand2))
    
    return sum([strand1[i] != strand2[i] for i in range(shorter)]) + (longer - shorter)
