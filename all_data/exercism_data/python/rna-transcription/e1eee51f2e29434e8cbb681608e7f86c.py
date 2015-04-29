def to_rna(sequence):
	result =""
	for char in sequence:
		if char == 'C':
			result += 'G'
		elif char == 'G':
			result += 'C'
		elif char == 'T':
			result += 'A'
		elif char == 'A':
			result += 'U'
	return result
