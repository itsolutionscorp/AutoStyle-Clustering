def distance(strand1, strand2):
	result = 0
	for x, y in zip(strand1, strand2):
		if x != y:
			result = result + 1
	return result
