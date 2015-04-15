# Python Script
# Author: Adam Gausmann


def distance(dna1, dna2):
    assert len(dna1) == len(dna2)
    
    result = 0;
    
    for n1, n2 in zip(dna1, dna2):
        if n1 != n2:
            result += 1
            
    return result
