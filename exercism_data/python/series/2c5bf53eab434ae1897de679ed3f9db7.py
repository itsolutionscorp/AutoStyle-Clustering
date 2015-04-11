def slices(string, length):
	result = []
	if length > len(string) or length == 0:
		raise ValueError( "{}: does not satisfy requirements".format(length) )
	for n in range(len(string)):
		items = string[n:n+length]
		if len(items) == length:
			result.append(map(int, list(items)))
	return result
