def to_rna(dna):
	rna=""
	dna_rna={'G':'C','C':'G','T':'A','A':'U'}
	for char in range(len(dna)):
		rna+=dna_rna[dna[char]]
	return rna
