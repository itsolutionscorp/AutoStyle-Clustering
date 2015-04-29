def hamming(sequence1, sequence2):
	mistakes = abs(len(sequence1) - len(sequence2));
	for i in range(min(len(sequence1), len(sequence2))):
		if sequence1[i] != sequence2[i]:
			mistakes += 1
	return mistakes
