def distance(seq_1, seq_2):
	# Calculates the Hamming distance between two given sequences
	score = 0

	for i in range(len(seq_1)):
		if seq_1[i] != seq_2[i]:
			score += 1

	return score
