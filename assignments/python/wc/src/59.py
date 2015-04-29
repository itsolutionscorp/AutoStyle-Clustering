def word_count(sentence):
	words = {}
	wordslist = sentence.split()
	for i in wordslist:
		if i in words:
			words[i] += 1
		else:
			words[i] = 1
	return words
