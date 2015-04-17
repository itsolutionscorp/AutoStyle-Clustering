def word_count(words):
	c = {}
	words = words.split()
	for word in words:
		if word not in c:
			c[word] = 1
		else:
			c[word] += 1
	return c
