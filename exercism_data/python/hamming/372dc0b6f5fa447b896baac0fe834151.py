from itertools import izip_longest

def hamming(a, b):
    return reduce(lambda x,y: x + (1 if y[0] != y[1] else 0), izip_longest(a, b), 0)
