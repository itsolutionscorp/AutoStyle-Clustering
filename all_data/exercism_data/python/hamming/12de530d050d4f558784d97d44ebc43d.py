def hamming(dna_1,dna_2):
	counter=0
	diff_list = ""

	longer_dna = ""
	shorter_dna = ""

	if len(dna_1) > len(dna_2):
		longer_dna = dna_1
		shorter_dna = dna_2
	else:
		longer_dna = dna_2
		shorter_dna = dna_1

	len_difference = abs(len(longer_dna) - len(shorter_dna))

	if len_difference > 0:
		remaining_char = longer_dna[-len_difference:]
		for x in remaining_char:
			shorter_dna += '*'

	for x in longer_dna:
		if x != shorter_dna[counter]:
			diff_list += x
		counter += 1

	return len(diff_list)
