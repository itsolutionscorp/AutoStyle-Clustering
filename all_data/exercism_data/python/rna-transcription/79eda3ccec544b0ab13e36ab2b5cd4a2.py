def to_rna(dna):
	rna = dna.translate(str.maketrans('GCTA', 'CGAU'))
	return rna
