def word_count(sentence):
	count = {}
	for line in sentence.splitlines():
		for word in line.split():
			count[word] = count.get(word, 0) + 1

	return count
