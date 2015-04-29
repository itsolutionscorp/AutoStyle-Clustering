def parse_binary(string):
	if any(char not in '01' for char in string):
		raise ValueError('The string should only consist of zeros and ones.')
	
	total = 0
	for char in string:
		total += total + int(char)

	return total
