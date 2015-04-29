'''
Created on Sep 24, 2014

@author: dhawkins
'''

def hamming(dna1, dna2):
    dist = abs(len(dna1) - len(dna2))
    
    for i, c in enumerate(dna1):
        try:
            if c != dna2[i]:
                dist += 1
        except IndexError:
            break
    
    return dist
