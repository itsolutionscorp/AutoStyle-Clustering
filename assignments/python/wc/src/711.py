def word_count(sentence):
	words = sentence.split()
	result = {}
	for w in words:
		result[w] = words.count(w)
	return result
