#calculates the 'hamming difference' of two DNA sequences
def distance(dna1, dna2):
	if len(dna1) == len(dna2):
		ham_count = 0
		#index works for both seq because the lengths must be equal
		for index in range(len(dna1)):
			#if the nucleotides at this point differ...
			if dna1[index] != dna2[index]:
				ham_count += 1
		return ham_count	

	else:
		#the hamming difference isn't defined for sequences of different lengths
		return None
