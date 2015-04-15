def word_count(sentence):
	numwords = {}
	for word in sentence.split():
		if numwords.has_key(word):
			numwords[word] = numwords[word] + 1
		else:
			numwords.update({word : 1})
	return numwords
