from itertools import izip

def hamming(dna1, dna2):
    diff = abs(len(dna1)-len(dna2))
    diff += sum(d1 != d2 for d1, d2 in izip(dna1, dna2))
    return diff

    
