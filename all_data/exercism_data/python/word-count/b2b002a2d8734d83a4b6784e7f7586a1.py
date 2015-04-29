def word_count(sentence):
	word_counter = {}

	# Split sentence on whitespaces and return a list of words
	words = sentence.split()

	for word in words:
		# Word was already found
		if word in word_counter:
			word_counter[word] += 1

		# Word found for the first time
		else:
			word_counter[word] = 1

	return word_counter
