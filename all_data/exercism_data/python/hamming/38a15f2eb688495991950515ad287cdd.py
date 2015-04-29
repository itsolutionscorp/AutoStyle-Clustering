__author__ = 'Greg'

def hamming(a, b):
    """ counts the number of characters varying between 2 strings."""
    len1 = len(a)
    len2 = len(b)
    overlap = min(len1, len2)
    difference = abs(len1 - len2)
    difference += sum([1 for x in range(overlap) if a[x] != b[x]])
    return difference
