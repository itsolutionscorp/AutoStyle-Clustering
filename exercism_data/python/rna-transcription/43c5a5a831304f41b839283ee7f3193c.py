# transcribe a DNA sequence to its RNA pair
def to_rna(dna):
	base_pairs = {'C':'G', 'G':'C', 'A':'U', 'T':'A' }
	rna_list = []

	for base in dna:
		rna_list.append(base_pairs[base])
	return ''.join(rna_list)
