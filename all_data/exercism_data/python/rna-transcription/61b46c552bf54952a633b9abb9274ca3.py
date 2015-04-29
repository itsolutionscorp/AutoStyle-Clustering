def to_rna(dna):
	dna_to_rna = {
		'G':'C',
		'C':'G',
		'T':'A',
		'A':'U',	
	}
	rna = []
	for nucleotide in dna:
		rna.append(dna_to_rna[nucleotide])
	return "".join(rna)
