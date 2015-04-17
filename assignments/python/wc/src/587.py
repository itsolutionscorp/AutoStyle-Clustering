def word_count(s):
	s = s.split()
	res = {}
	for c in s:
		if c not in res:
			res[c] = 1
		else:
			res[c] += 1
	return res
