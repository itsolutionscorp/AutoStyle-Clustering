def to_rna(rna):
	a = list(rna)
	for i, x in enumerate(a):
		if x == 'C':
			a[i]='G'
		if x == 'G':
			a[i]='C'
		if x == 'T':
			a[i]='A'
		if x == 'A':
			a[i]='U'	
	return ''.join(a)
