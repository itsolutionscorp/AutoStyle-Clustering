def word_count(sentence):
	hist = {}
	for word in sentence.split():
		hist[word] = hist.get(word, 0) + 1
	return hist
