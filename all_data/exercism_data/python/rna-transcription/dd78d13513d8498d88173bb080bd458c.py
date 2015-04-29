def to_rna(dna_strand):
	dna_complement_dict = {'A' : 'U', 'C' : 'G', 'G' : 'C', 'T' : 'A'}
	rna_strand = ""
	for protein in dna_strand:
		rna_strand = rna_strand + dna_complement_dict[protein]
	return rna_strand
