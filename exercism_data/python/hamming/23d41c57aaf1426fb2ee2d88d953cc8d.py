def distance(strand_1, strand_2):
	
	hamming_distance = 0

	for i in range(0, len(strand_1)):
		if strand_1[i] != strand_2[i]:
			hamming_distance+=1
	
	return hamming_distance
