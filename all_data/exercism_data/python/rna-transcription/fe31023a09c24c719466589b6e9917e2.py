def to_rna(dna):
	return (dna.replace('G', 'tmp')
		       .replace('C', 'G')
		       .replace('tmp', 'C')
		       .replace('A', 'U')
		       .replace('T', 'A'))
