# -*- coding: utf-8 -*-

def distance(dna1,dna2):
    h = 0    
    
    for x,y in zip(dna1,dna2):
        if x != y:
            h += 1
    return h
        
    
