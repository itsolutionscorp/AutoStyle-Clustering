def distance(sequence1, sequence2):
	distance = 0
	for base1, base2 in zip(sequence1, sequence2):
		if (base1 != base2):
			distance += 1
	return distance
