def word_count(words):
	wc = {}
	for word in words.split():
		wc[word] = wc.get(word, 0) + 1
	return wc
