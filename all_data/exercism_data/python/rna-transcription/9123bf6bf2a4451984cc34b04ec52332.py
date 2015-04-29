def to_rna(dna):
	trans_table = {'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	return ''.join([trans_table[nuc] for nuc in dna])
