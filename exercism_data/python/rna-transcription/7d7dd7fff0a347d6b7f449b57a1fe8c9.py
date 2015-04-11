def to_rna(dna_sequence):
	rna_complement = {'A': 'U', 'T': 'A', 'C': 'G', 'G': 'C'}
	rna_sequence = ""

	for nucleotide in dna_sequence:
		rna_sequence += rna_complement[nucleotide]

	return rna_sequence
