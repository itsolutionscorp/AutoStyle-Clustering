def to_rna(dna):
	rna = ""
	for nucleotide in list(dna):
		if nucleotide == "G":
			rna += "C"
		elif nucleotide == "C":
			rna += "G"
		elif nucleotide == "T":
			rna += "A"
		else:
			rna += "U"
	return rna
