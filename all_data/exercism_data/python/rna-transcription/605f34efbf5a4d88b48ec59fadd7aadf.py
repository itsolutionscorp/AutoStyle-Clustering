def to_rna(strand):
	result = ''
	for i in range(len(strand)):
		if strand[i]=='G':
			result += 'C'
		elif strand[i]=='C':
			result += 'G'
		elif strand[i]=='T':
			result += 'A'
		elif strand[i]=='A':
			result += 'U'
	return result
