DNADICT = {
	'G': 'C',
	'C': 'G',
	'T': 'A',
	'A': 'U',
}
def to_rna(dna):
	if len(dna) == 1:
		return DNADICT[dna[0]]
	return DNADICT[dna[0]] + to_rna(dna[1:])
