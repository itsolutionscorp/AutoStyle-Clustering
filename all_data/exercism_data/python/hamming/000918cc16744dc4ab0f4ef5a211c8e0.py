def distance(strand1, strand2):
	result = 0
	for x in zip(strand1, strand2):
		if x[0] != x[1]:
			result = result + 1
	return result
