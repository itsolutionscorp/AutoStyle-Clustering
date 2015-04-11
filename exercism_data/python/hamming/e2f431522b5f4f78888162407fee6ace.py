def distance(firstStrand, secondStrand):
	return sum(charA != charB for charA, charB in zip(firstStrand, secondStrand))
