def word_count(string):
	string = ''.join(c.lower() for c in string if c.isalnum() or c.isspace())
	words = {}
	for w in string.split():
		if w in words:
			words[w] += 1
		else:
			words[w] = 1
	return words
