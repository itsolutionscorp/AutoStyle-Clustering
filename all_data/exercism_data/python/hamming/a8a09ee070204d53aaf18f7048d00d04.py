from itertools import izip_longest

def hamming(seq1, seq2):
    dna_compare = list(izip_longest(seq1, seq2))
    diff = 0
    for n1, n2 in dna_compare:
        if n1 != n2:
            diff += 1
    
    return diff
