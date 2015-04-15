
def distance(strandA, strandB):
	test_length = len(strandA) if len(strandA) > len(strandB) else len(strandB)
	hamming_distance = 0
	for i in xrange(test_length):
		if strandA[i] != strandB[i]:
			hamming_distance += 1
	return hamming_distance
