def to_rna(dna):
	pairing = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	dna = list(dna)
	rna = [pairing[i] for i in dna]
	rna = ''.join(rna)
	return rna
	
	


	
