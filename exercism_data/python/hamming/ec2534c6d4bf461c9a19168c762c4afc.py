def distance(seq_1, seq_2):
	hamming_diff = 0
	for indx in range(len(seq_1)):
		if seq_1[indx] != seq_2[indx]:
			hamming_diff+=1
	return hamming_diff
