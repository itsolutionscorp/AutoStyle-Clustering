from itertools import izip_longest

def hamming(seq1, seq2):
    return sum([x != y for x, y in izip_longest(seq1, seq2, fillvalue=None)])
