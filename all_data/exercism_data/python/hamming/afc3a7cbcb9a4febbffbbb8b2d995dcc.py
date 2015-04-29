#calculates the 'hamming difference' of two DNA sequences
def distance(dna1, dna2):
	if len(dna1) == len(dna2):
		ham_count = 0
		for one, two in zip(dna1, dna2):
			#if the nucleotides at this point differ...
			if one != two:
				ham_count += 1
		return ham_count	

	else:
		#the hamming difference isn't defined for sequences of different lengths
		return None
