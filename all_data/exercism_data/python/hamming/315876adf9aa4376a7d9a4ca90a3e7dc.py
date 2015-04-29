from itertools import izip

def distance(strand1, strand2):
    d = 0
    for n1, n2 in izip(strand1, strand2):
        if n1 != n2:
            d += 1
    return d
