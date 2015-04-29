def slices(num, length):
	if len(num) < length or not length:
		raise ValueError

	num = map(int, list(num))
	return [num[start: start+length]
		for start in range(0, len(num) - length + 1)]
