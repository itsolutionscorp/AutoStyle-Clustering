#hamming

"""
example:

    GAGCCTACTAACGGGAT
    CATCGTAATGACGGCCT
    ^ ^ ^  ^ ^    ^^
"""

def distance(s1, s2):
	distance = 0
	for x, y in zip(s1, s2):
		if x != y:
			distance +=1
	return distance
