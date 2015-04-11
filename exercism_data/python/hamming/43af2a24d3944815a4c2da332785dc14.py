def hamming(initial_dna, mutated_dna):
	dna_size_difference = abs(len(initial_dna) - len(mutated_dna))
	dna_letter_difference = sum([letter1!=letter2 for letter1, letter2 in zip(initial_dna, mutated_dna)])
	return dna_letter_difference + dna_size_difference
