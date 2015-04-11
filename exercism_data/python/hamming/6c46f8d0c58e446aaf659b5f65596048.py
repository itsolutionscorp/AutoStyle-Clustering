"""
Exercism.io
Python Hamming

Author: jdcampo
"""

def hamming(strand1, strand2):
    diff = abs(len(strand1) - len(strand2))
    for b1, b2 in zip(strand1, strand2):
        if b1 != b2:
            diff += 1
    return diff
        
