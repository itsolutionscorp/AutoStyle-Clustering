def to_rna(strand):
	rna = []
	for item in strand:
		if item == "G":
			rna.append("C")
		elif item == "C":
			rna.append("G")
		elif item == "A":
			rna.append("U")
		elif item == "T":
			rna.append("A")
	return ''.join(rna)
