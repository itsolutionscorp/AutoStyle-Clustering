from itertools import izip_longest

def hamming(first_strand, second_strand):
    return sum(1 for x, y in izip_longest(first_strand, second_strand) if x != y)
