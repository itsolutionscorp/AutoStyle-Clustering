def to_rna(strand):
	rna = ''
	for item in strand:
		if item == "G":
			rna += "C"
		elif item == "C":
			rna += "G"
		elif item == "A":
			rna += "U"
		elif item == "T":
			rna += "A"
	return rna
