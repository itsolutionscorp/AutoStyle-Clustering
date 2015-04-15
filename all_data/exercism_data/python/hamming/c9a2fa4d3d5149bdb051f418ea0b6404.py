def distance(strand1, strand2):
	result = 0
	for i, j in zip(strand1, strand2):
		if i != j:
			result += 1
	return result
