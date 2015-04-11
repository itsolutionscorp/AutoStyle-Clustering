from itertools import izip_longest


def hamming(seq1, seq2):
    return sum(a != b for a, b in izip_longest(seq1, seq2, fillvalue=''))
