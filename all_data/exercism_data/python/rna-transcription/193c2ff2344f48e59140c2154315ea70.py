def to_rna(strand):
	
	rnaString = ""
	
	for nucleotide in strand:
		if nucleotide == "C":
			rnaString = rnaString + "G"
		if nucleotide == "G":
			rnaString = rnaString + "C"
		if nucleotide == "T":
			rnaString = rnaString + "A"
		if nucleotide == "A":
			rnaString = rnaString + "U"
			
	return rnaString
