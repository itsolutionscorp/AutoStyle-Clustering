def distance(strand1, strand2):
	'''Calculates the Hamming difference between two DNS strands'''
	diff = 0
	if (len(strand1) == len(strand2)):
		for i in range(len(strand1)):
			if strand1[i] != strand2[i]:
				diff += 1
	return diff
