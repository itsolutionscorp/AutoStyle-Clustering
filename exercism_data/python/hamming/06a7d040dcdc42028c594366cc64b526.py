from itertools import izip_longest

def hamming(string1, string2):
	iterator = izip_longest(string1, string2)
	return sum(char1 != char2 for char1, char2 in iterator)
