def to_rna(strand):
	rna_strand = []
	
	for s in strand:
		if s == "G":
			rna_strand.append('C')
		if s == "C":
			rna_strand.append('G')
		if s == "T":
			rna_strand.append('A')
		if s == "A":
			rna_strand.append('U')

	rna_str = ''.join(rna_strand)
	return rna_str