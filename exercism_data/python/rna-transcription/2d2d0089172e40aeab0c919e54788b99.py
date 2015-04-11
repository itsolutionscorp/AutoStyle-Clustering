def to_rna(dna):
	dna_rna = {'G': 'C', 'C': 'G', 'T': 'A', 'A': 'U'}
	nucleotids = list(dna)
	rna_list = []
	for key in nucleotids:
		rna_list.append(dna_rna[key])
	rna = "".join(rna_list)
	return rna
