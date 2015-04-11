# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 21:09:03 2014

@author: luis
"""

def distance(seq1,seq2):
    distance=0
    alignment=zip(seq1,seq2)
    for item in alignment:
        if item[0]!=item[1]:
            distance+=1
    return distance
    
        
