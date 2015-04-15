def to_rna(dna_strand):
	rna=""
	for letter in range(len(dna_strand)):
		if dna_strand[letter]=='C':
			rna+='G'
			continue
		if dna_strand[letter]=='G':
			rna+='C'
			continue
		if dna_strand[letter]=='T':
			rna+='A'
			continue
		if dna_strand[letter]=='A':
			rna+='U'
			continue
		rna+=dna_strand[letter]
	dna_strand=rna
	return dna_strand
