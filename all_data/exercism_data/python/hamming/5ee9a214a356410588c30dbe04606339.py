def distance(originalDna, mutatedDna):
	hamDistance = 0
	for tup in zip(originalDna, mutatedDna):
		if tup[0] != tup[1]:
			hamDistance += 1
	return hamDistance
