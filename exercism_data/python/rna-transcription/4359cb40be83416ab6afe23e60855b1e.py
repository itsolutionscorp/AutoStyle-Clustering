def to_rna(dna):
	_to_rna = {'G': 'C' ,'C': 'G' ,'T': 'A' ,'A': 'U'}
	return ''.join([_to_rna[x] for x in dna])
