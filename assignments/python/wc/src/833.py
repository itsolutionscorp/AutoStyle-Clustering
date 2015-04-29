def word_count(text):
	word_frequencies = dict()
	words = text.split()
	for word in words:
		word_frequencies[word] = word_frequencies.get(word, 0) + 1
	return word_frequencies
