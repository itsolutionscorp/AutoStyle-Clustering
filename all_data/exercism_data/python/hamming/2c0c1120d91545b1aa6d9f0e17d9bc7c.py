def distance(sequence1, sequence2):
#Takes two genetic sequence and returns
#the hamming distance between the two
	hamming = 0
	for i in range(0, len(sequence1)):
		if sequence1[i] != sequence2[i]:
			hamming += 1
			
	return hamming
