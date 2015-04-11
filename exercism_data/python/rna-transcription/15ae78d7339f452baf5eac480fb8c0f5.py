def to_rna(strand):
	dna_to_rna = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	
	ret = [dna_to_rna[c] for c in strand]
	
	return ''.join(ret)
