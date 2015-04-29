def to_rna(sequence):
	rna_sequence = ""

	for nucleotides in sequence:
		if nucleotides == "G": rna_sequence += "C"
		elif nucleotides == "C": rna_sequence += "G"
		elif nucleotides == "T": rna_sequence += "A"
		elif nucleotides == "A": rna_sequence += "U"

	return rna_sequence
