from itertools import izip_longest

def hamming(strand1,strand2):
	#wow, this is a lot faster, thanks @Octowl
	return sum(1 for x,y in izip_longest(strand1,strand2) if x != y)
