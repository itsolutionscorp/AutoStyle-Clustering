def distance(strand1, strand2):
	
	hamming_distance = 0

	for i in range(len(strand1)):
		if not strand1[i]==strand2[i]:
			hamming_distance += 1
	
	return hamming_distance
