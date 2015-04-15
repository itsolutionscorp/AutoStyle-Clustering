def to_rna(letters):
	newDna = ''
	for i,c in enumerate(letters):
		if letters[i] == 'A':
			newDna += 'U'
		elif letters[i] == 'T':
			newDna += 'A'
		elif letters[i] == 'C':
			newDna += 'G'
		elif letters[i] == 'G':
			newDna += 'C'
	return newDna
