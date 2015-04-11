def to_rna(dna):
	dna_list = list(dna)
	for i in range(len(dna_list)):
		if dna_list[i] == "G":
			dna_list[i] = "C"
		elif dna_list[i] == "C":
			dna_list[i] = "G"
		elif dna_list[i] == "T":
			dna_list[i] = "A"
		elif dna_list[i] == "A":
			dna_list[i] = "U"
	dna = ""
	for i in range(len(dna_list)):
		dna = dna + dna_list[i]
	return dna
			
		
	
	
