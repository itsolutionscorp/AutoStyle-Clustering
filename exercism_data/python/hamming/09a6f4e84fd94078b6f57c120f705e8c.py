from itertools import izip_longest

def hamming(dna1, dna2):
    res = 0
    for a, b in izip_longest(dna1, dna2):
        if a != b:
            res += 1
    return res
