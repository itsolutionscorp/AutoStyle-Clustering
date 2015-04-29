def distance(strand1, strand2):
	result = 0
	for i, c in enumerate(strand1):
		if strand2[i] != c:
			result = result + 1
	return result
