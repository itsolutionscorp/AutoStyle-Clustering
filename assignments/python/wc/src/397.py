def word_count(words):
	wordCounts = {}
	words = words.split()
	for word in words:
		if word in wordCounts:
			wordCounts[word] += 1
		else:
			wordCounts[word] = 1
	return wordCounts
