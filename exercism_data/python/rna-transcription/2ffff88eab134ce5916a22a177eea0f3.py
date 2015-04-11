def to_rna(dna):
	rna = ""
	for base in dna:
		if base == 'C':
			rna+='G'
		elif base == 'G':
			rna+='C'
		elif base == 'A':
			rna+='U'
		else:
			rna+='A'
	return rna

print to_rna('CTGAGATTACA')
