def word_count(t):
	d = {}
	for token in t.split():
		if token not in d:
			d[token] = 1
		else:
			d[token] += 1
	return d
