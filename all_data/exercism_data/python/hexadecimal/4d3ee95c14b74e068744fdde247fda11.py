hexdigits = { a : nr for nr, a in enumerate("0123456789abcdef") }

def hexa(string):
	string = string.lower()

	if any(char not in hexdigits for char in string):
		raise ValueError('The string contains invalid characters.')
	
	total = 0
	for char in string:
		total = 16 * total + hexdigits[char]

	return total
