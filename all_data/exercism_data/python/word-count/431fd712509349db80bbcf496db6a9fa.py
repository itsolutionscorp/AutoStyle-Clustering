def word_count(sentence):
	words = sentence.split()
	word_count = dict()
	for word in words:
		if word_count.has_key(word):
			word_count[word] += 1
		else:
			word_count[word] = 1
	return word_count
