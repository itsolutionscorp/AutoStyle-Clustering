def to_rna(dnaString):
	rnaString = ''
	complement = {'C':'G', 'G':'C','T':'A','A':'U'}
	for char in dnaString:
		rnaString += complement[char]
	
	return rnaString
