def slices(s, n):
	if n <= 0:
		raise ValueError('Slices of 0 length do not make sense!')
	if n > len(s):
		raise ValueError('Slice length can not exceed length of the string to slice')
	s = map(int, s)
	return [s[i:i+n] for i in range(len(s) - n + 1)]
