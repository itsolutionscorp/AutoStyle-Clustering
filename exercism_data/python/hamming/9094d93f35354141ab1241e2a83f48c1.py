def distance(stringA,stringB):
	'''
	Calculates Hamming difference between two strings
	'''
	difCount = 0
	
	# checks that the two strands are of the same length
	assert len(stringA) == len(stringB)
	
	# checks for equality of each string for each index
	for i in range(len(stringA)):
		if stringA[i] != stringB[i]:
			difCount += 1

	return difCount
