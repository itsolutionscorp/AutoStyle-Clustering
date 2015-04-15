def hamming(x1, x2):
	differences = abs(len(x2)-len(x1))
	for i in range(0, min(len(x1), len(x2))):
		if x1[i] != x2[i]:
			differences += 1
	return differences
