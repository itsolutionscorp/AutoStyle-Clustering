from itertools import izip_longest


def hamming(strand_a, strand_b):
	return sum(1 if a != b else 0 for a,b in izip_longest(strand_a, strand_b))
