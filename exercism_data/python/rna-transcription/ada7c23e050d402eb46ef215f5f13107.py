def to_rna(dna):
	result = ''

	for char in dna:
		if char == 'G':
			result += 'C'
		if char == 'C':
			result += 'G'
		if char == 'T':
			result += 'A'
		if char == 'A':
			result += 'U'

	return result
