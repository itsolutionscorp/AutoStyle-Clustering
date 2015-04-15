def word_count(what):
	words = dict()
	for w in what.split():
		if w in words:
			words[w] = words[w]+1
		else:
			words[w] = 1
	return words
