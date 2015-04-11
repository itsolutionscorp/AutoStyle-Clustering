def to_rna(dna_sequence):
	dna_to_rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	rna_sequence = ""
	for dna_strand in dna_sequence:
		rna_sequence += dna_to_rna[dna_strand]

	return rna_sequence
