def to_rna(dna):
	conversion = {
		'G' : 'C',
		'C' : 'G',
		'T' : 'A',
		'A' : 'U'
	}
	string = ''
	for r in dna:
		string += conversion[r]
	return string
