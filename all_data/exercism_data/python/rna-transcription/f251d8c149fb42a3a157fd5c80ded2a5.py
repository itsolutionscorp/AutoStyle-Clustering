def to_rna(dna):
	dna = dna.upper()
	rna = dna.replace('G','%temp%').replace('C','G').replace('%temp%','C')
	rna = rna.replace('A','U')
	rna = rna.replace('T','A')
	return rna


	
