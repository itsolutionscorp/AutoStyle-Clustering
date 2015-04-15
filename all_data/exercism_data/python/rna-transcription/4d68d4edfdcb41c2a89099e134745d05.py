def to_rna(dna):
	string = ''
	for i in dna:
		if i == 'G':
			string = string + 'C'
		elif i == 'C':
			string = string + 'G'
		elif i == 'T':
			string = string + 'A'
		elif i == 'A':
			string = string + 'U'
	return string
	
