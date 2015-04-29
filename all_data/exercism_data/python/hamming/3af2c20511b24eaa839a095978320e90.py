
def hamming_distance(sequenceOne, sequenceTwo):
	dnaOne = list(sequenceOne)
	dnaTwo = list(sequenceTwo)
	distance = 0
	
	for x, y in zip(dnaOne, dnaTwo):
		if (x != y):
			distance += 1
	
	return distance
