#hamming

"""
example:

    GAGCCTACTAACGGGAT
    CATCGTAATGACGGCCT
    ^ ^ ^  ^ ^    ^^
"""

def distance(s1, s2):
	diff = 0
	for x, y in zip(s1, s2):
		if x != y:
			diff +=1
	return diff 
