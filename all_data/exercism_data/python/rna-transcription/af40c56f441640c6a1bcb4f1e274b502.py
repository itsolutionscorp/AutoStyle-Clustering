def to_rna(dna):
	translation = dict(zip("GCTA", "CGAU"))
	returnString = ""
	for nucleotide in dna:
		returnString += translation[nucleotide]
	return returnString
