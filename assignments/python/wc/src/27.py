def word_count(sentence):
	word_counter = {}
	words = sentence.split()
	for word in words:
		word_counter[word] = word_counter.get(word, 0) + 1
	return word_counter
