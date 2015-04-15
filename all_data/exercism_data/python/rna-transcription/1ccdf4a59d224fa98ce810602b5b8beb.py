substitution_table = {
	'C': 'G',
	'G': 'C',
	'T': 'A',
	'A': 'U'
}

def to_rna(sequence):
	rna = []
	for nucleotide in sequence:
		rna.append(substitution_table[nucleotide])
	return "".join(rna)
