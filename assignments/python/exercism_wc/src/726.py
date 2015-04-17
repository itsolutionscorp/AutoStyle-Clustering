def word_count(s):
	words = s.split()
	words.sort()
	c = {}
	for w in words:
		if w in c:
			c[w] += 1
		else:
			c[w] = 1
	return c
