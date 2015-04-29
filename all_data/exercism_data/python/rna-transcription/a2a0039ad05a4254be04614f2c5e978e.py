def to_rna(dna):
	rna=[]
	for i in range(len(dna)):
		if dna[i]=='G':
			rna.append('C')
		elif dna[i]=='C':
			rna.append('G')
		elif dna[i]=='T':
			rna.append('A')
		elif dna[i]=='A':
			rna.append('U')
		else:
			rna.append(dna[i])
	return ''.join(rna)
	
