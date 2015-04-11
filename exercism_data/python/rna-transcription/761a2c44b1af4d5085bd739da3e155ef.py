def to_rna(strand):
	rna_mapping = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
	rna = ''
	for letter in strand:
		rna = rna + rna_mapping[letter]
	return rna
