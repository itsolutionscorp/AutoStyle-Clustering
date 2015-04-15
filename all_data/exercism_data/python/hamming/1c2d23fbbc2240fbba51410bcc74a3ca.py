from itertools import izip_longest

def hamming(a, b):
    return sum((1 for x,y in izip_longest(a, b) if x != y))
