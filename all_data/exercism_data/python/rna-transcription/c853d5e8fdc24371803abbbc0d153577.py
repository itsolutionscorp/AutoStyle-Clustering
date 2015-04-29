def to_rna(dna_str):
	mapping = {'G' : 'C',
		   'C' : 'G',
		   'T' : 'A',
		   'A' : 'U'}
	return "".join([mapping[letter] for letter in dna_str])
