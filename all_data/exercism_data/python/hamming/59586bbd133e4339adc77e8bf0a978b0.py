import itertools
def distance(dna1, dna2): return sum(itertools.imap(str.__ne__, dna1, dna2))
