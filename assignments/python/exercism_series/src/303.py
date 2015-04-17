def slices(s, n):
	if n <= 0 or n > len(s):
		raise ValueError("Bad slice length")
	res = []
	s = [int(x) for x in s]
	for i in range(len(s) - n + 1):
		res.append(s[i:i+n])
	return res
