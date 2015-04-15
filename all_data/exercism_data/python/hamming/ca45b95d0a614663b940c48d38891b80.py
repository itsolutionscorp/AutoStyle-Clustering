import operator

def hamming(strand_one, strand_two):
	return sum(map(operator.ne, strand_one, strand_two))
