def word_count(s):
	d = {}
	for word in s.split():
		try:
			d[word] += 1
		except KeyError:
			d[word] = 1
	return d
