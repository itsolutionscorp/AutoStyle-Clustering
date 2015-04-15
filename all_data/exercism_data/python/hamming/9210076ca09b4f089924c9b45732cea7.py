def distance(dna1, dna2):
	longest = None
	if len(dna1) >= len(dna2):
		longest = dna1
	else:
		longest = dna2
	dist = 0
	for i in range(len(longest)):
		if dna1[i] != dna2[i]:
			dist += 1
	return dist
