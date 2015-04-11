def hamming(dna1, dna2):
	'''
	Calculating the Hamming difference between
	two DNA strands
	'''

	# find the number of differences between
	# the equal length portions of both strings
	diff = sum(1 for x,y in zip(dna1, dna2) if x != y)

	# add the difference in length
	diff += abs(len(dna1) - len(dna2))

	return diff
