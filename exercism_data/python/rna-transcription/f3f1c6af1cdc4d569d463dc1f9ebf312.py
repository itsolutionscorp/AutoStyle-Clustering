def to_rna(dna):
	output = ''
	complements = {"G": "C", "C": "G", "T": "A", "A": "U"}
	for nucleotide in dna:
		output += complements[nucleotide]
	return output
