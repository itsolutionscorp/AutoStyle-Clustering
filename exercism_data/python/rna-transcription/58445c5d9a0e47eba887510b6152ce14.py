def to_rna(str):
	rna = ""
	for ch in str:
		if ch == 'C':
			rna = rna + 'G'
		elif ch == 'G':
			rna = rna + 'C'
		elif ch == 'T':
			rna = rna + 'A'
		elif ch == 'A':
			rna = rna + 'U'
	return rna
		
