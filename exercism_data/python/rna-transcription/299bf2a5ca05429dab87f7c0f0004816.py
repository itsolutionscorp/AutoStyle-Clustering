def to_rna(strand):
	complements = {'A': 'U', 'T': 'A', 'C': 'G', 'G': 'C'}
	new_strand = ""
	for nucleotide in strand:
		new_strand += complements[nucleotide]
	return new_strand
