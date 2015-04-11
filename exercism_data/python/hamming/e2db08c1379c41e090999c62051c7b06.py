def distance(strand1, strand2):
	hamming_distance = 0
	if len(strand1) != len(strand2):
		raise Exception("Strands are not the same length")
	
	for i in range(len(strand1)):
		if strand1[i] != strand2[i]:
			hamming_distance += 1
		
	return hamming_distance
