def distance(a, b):
	result = 0
	zipped = zip(a, b)
	for i, j in zipped:
		if i != j:
			result += 1
	return result
