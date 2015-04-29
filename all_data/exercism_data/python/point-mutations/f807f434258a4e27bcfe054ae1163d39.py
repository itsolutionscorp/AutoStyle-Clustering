import itertools


def hamming_distance(strand_1, strand_2):
    return sum(x != y for x, y in itertools.izip(strand_1, strand_2))
