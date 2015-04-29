def hamming(strand1,strand2):

	# Initialize Hamming distance variable
	H = 0

	# Compute length of longest input strand
	L = max(len(strand1),len(strand2))

	# Pad strand strings with space to make equal length
	strand1 = strand1.ljust(L)
	strand2 = strand2.ljust(L)

	# Increment Hamming distance for each differing character
	for i in range(L):
		if strand1[i] != strand2[i]: H += 1

	return H 
