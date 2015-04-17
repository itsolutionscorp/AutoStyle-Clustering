def word_count(txt):
	result = {}
	for w in txt.split():
		if w in result:
			result[w] += 1
		else:
			result[w] = 1
	return result
