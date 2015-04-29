def to_rna(dna):
	result = ""
	for c in dna:
		if c == 'G':
			result += 'C'
		if c == 'C':
			result += 'G'
		if c == 'T':
			result += 'A'
		if c == 'A':
			result += 'U'
	return result
