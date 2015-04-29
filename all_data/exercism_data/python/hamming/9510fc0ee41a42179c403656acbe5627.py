def distance(firstStrand, secondStrand):
	return sum(pair[0] != pair[1] for pair in zip(list(firstStrand), list(secondStrand)))
