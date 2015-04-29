#hamming.py

# compute the Hamming distance between two DNA strands
def distance(strand_1, strand_2):
	difference = 0

	# the hamming distance is underfined for strands of unequal length
	if (len(strand_1) != len(strand_2)):
		return 0

	# the hamming distance is the number of different characters at the same
	# index in two strings
	for i in range(len(strand_1)):
		if (strand_1[i] != strand_2[i]):
			difference+=1

	return difference
