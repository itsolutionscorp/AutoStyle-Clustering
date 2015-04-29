def to_rna(dna_string):
	synthesize = { 'G':'C', 'C':'G', 'T':'A', 'A':'U'}
	rna = ''
	for nucleotide in dna_string:
		#check if we have a valid entry
		if nucleotide in synthesize:
			rna+=synthesize[nucleotide]
		else:
			#invalid entry
			break
	return rna
