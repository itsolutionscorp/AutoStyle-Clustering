def to_rna(dna):
	rna = ""
	for x in range(0,len(dna)):
		if dna[x] == "G":
			rna = rna + "C"
		elif dna[x] == "C":
			rna = rna + "G"
		elif dna[x] == "T":
			rna = rna + "A"
		elif dna[x] == "A":
			rna = rna + "U"
	return rna
