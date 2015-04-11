def distance(strand1, strand2):
	
	assert (len(strand1) == len(strand2)), 'Hamming is only defined for stands of the same length'
	
	hamming = 0
	for i in range(len(strand1)):
		if strand1[i] != strand2[i]:
			hamming += 1
	
	return hamming
