def to_rna(dna):
	rna = []
	for x in range(0,len(dna)):
		if dna[x] == "G":
			rna.append("C")
		elif dna[x] == "C":
			rna.append("G")
		elif dna[x] == "T":
			rna.append("A")
		elif dna[x] == "A":
			rna.append("U")
	return "".join(rna)
