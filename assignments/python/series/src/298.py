def slices(string, length):
	if length == 0 or length > len(string):
		raise ValueError
	results = []
	for i, char in enumerate(string):
		potential = string[i:i+length]
		if len(potential) == length:
			results.append(list(map(int, list(potential))))
	return results
