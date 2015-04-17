def word_count(sentence):
	word_counter = {}
	words = sentence.split()
	for word in words:
		if word in word_counter:
			word_counter[word] += 1
		else:
			word_counter[word] = 1
	return word_counter
