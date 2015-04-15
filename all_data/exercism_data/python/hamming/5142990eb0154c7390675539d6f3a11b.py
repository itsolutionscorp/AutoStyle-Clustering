from itertools import izip

def distance(seq1, seq2):
    return sum(1 for a, b in izip(seq1, seq2) if a != b)
