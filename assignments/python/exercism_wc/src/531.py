def word_count(some_words):
	countWords = {}
	for word in some_words.split():
		if word not in countWords:
			countWords[word] = 1
		else: # in wordDict
			countWords[word] += 1
	return countWords
