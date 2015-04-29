__author__ = 'Greg'

def hamming(a, b):
    """ counts the number of characters varying between 2 strings."""
    lenA, lenB = len(a), len(b)
    return sum([1 for x in range(min(lenA, lenB)) if a[x] != b[x]]) + abs(lenA -
               lenB)
    
