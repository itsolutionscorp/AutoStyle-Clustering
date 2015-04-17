def word_count(words):
	x = {}
	s = str(words).split()
	for word in s:
		if word in x:
			x[word] += 1
		else:
			x[word] = 1
	return x
