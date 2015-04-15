"""This module implements a simple Hamming distance calculator
for measuring the number of point mutations between two DNA base
strings."""

def distance(s1, s2):
    """Returns the Hamming distance between two strings. 
    Will return -1 if strings are not of the same length."""
    if len(s1) != len(s2):
        return -1  # could do some fancy exit error but nfi how
    
    n = 0
    for i in range(len(s1)):
        if s1[i] != s2[i]:
            n += 1
            
    return n
