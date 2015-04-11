def to_rna(dna):
	result = ''
	transcript = { 'G' : 'C', 'C': 'G', 'T' : 'A', 'A' : 'U' }
	for strand in dna:
		result += transcript[strand]

	return result
