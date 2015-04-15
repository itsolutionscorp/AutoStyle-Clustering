def to_rna(input):

	output = ''
	for letter in input:
		if letter == 'A':
			output = output + 'U'
		if letter == 'T':
			output = output + 'A'
		if letter == 'G':
			output = output + 'C'
		if letter == 'C':
			output = output + 'G'
	return output
