def to_rna(string):
	
	dna_to_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}

	result = []
	for char in string:
		result.append(dna_to_rna[char])
	return ''.join(result)
