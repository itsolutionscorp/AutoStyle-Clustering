def to_rna(strand):
	complements = {'A': 'U', 'T': 'A', 'C': 'G', 'G': 'C'}
	return "".join([complements[nucleotide] for nucleotide in strand])
