from itertools import zip_longest

def hamming(first, second):
	return sum(x != y for x, y in zip_longest(first, second))
