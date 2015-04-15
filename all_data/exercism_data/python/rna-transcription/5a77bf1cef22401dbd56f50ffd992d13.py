def to_rna(str):
	"""
	Input: DNA as a string.
	Output: RNA as a string.
	"""
	output = ""
	for char in str:
		if char == 'G':
			output += 'C'
		elif char == 'C':
			output += 'G'
		elif char == 'T':
			output += 'A'
		else:
			output += 'U'
	return output
