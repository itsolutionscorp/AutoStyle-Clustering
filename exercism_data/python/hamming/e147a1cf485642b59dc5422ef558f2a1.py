def distance(strand_1, strand_2):
	
	hamming_distance = 0

	for one, two in zip(strand_1, strand_2):
		if one != two:
			hamming_distance+=1
	
	return hamming_distance
