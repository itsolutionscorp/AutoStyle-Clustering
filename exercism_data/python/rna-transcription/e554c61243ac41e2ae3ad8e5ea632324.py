def to_rna(dna):
	translation = {'G':'C','C':'G','T':'A','A':'U'}
	returnString = ""
	for nucleotide in dna:
		returnString += translation[nucleotide]
	return returnString
