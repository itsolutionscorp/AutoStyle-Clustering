def to_rna(dna):
	rna = dna.replace('C', '1')
	rna = rna.replace('G', 'C')
	rna = rna.replace('1', 'G')
	rna = rna.replace('A', 'U')
	rna = rna.replace('T', 'A')
	return rna
