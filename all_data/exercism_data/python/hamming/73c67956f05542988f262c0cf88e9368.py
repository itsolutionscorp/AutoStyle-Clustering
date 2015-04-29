import itertools

def hamming(dna0, dna1):
    aggregate = itertools.izip_longest(dna0,dna1,'F')
    count = sum(1 for pair in aggregate if pair[0] != pair[1])
    return count
