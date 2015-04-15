def word_count(sentence):
	word_counter = {}

	# Split sentence on whitespaces and return a list of words
	words = sentence.split()

	for word in words:
		word_counter[word] = word_counter.get(word, 0) + 1

	return word_counter
