def slices(string,n):
	if n > len(string):
		raise ValueError
	if not n:
		raise ValueError
	ret = []
	i = 0
	while i + n <= len(string):
		ret.append([int(x) for x in string[i:i+n]])
		i += 1
	return ret
