def to_rna(dna):
	#string.split() is only for splitting words. Must use list to split chars
	dnaList=list(dna)
	#i is index, letter is element
	for i, letter in enumerate(dnaList):
		if letter == 'G':
			dnaList[i]='C'
		elif letter == 'C':
			dnaList[i] = 'G'
		elif letter == 'T':
			dnaList[i] = 'A'
		elif letter == 'A':
			dnaList[i] = 'U'
	return ''.join(dnaList)
