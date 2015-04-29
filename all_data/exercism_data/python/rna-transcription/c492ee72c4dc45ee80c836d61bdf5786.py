def to_rna(strand):
	rna_mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

	return ''.join(rna_mapping[letter] for letter in strand)
