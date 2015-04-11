def to_rna(dna_sequence):
	dnaToRnaDict = {
		'G': 'C',
		'C': 'G',
		'T': 'A',
		'A': 'U',
	}
	rna_sequence = ''
	for c in dna_sequence:
		rna_sequence += str(dnaToRnaDict[c])
	return rna_sequence
