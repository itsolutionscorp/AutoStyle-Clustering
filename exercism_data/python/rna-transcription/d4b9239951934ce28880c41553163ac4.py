

def to_rna(test_string):
	out_string = ''
	for letter in test_string:
		if letter == 'G':
			out_string += 'C'
		elif letter == 'C' :
			out_string += 'G'
		elif letter == 'T':
			out_string += 'A'
		elif letter == 'A':
			out_string += 'U'
		else:
			out_string += 'x'

	return(out_string)

