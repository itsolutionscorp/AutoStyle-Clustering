def distance(sequenceA, sequenceB):
	return sum(map(lambda x, y: x != y, sequenceA, sequenceB))
