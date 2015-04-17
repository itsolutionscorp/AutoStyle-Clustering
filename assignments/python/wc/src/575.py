def word_count(some_words):
	count_words = {}
	for word in some_words.split():
		if word not in count_words:
			count_words[word] = 1
		else: # in wordDict
			count_words[word] += 1
	return count_words
