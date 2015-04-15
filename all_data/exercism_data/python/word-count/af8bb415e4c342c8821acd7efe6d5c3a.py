def word_count(sentence):
	words = dict()
	for w in sentence.split():
		if w in words.keys():
			words[w] += 1
		else:
			words[w] = 1
	return words
