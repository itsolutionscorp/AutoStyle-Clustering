def to_rna(dna):
	#Takes an input of a DNA string and returns its complimentary
	#rna string
	
	rna = ""
	for nucleotide in dna:
		if nucleotide == "G":
			rna += "C"
		elif nucleotide == "C":
			rna += "G"
		elif nucleotide == "T":
			rna += "A"
		else:
			rna += "U"
			
	return rna
