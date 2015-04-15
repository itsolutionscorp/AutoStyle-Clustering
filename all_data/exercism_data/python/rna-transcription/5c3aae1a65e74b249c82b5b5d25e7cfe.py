def to_rna(dna):

	rna_translate = { 'G':'C','C':'G','T':'A','A':'U' }
	
	dna = dna.upper()
	rna = ""
	for char in dna:
		rna += rna_translate[char]
	return rna
