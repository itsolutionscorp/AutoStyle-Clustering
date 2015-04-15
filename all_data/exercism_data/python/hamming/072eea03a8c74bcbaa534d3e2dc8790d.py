from itertools import izip

def distance(str1, str2):
    """Calculates the hamming distance between two equal length strings"""
    dist = 0
    for c1, c2 in izip(str1, str2):
        if c1 != c2: dist += 1
    return dist
