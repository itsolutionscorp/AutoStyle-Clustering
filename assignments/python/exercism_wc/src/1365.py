def word_count(phrase):
	phrase = phrase.split()
	wordCounts = {}
	for word in phrase:
		if word in wordCounts:
			wordCounts[word] += 1
		else:
			wordCounts[word] = 1
	return wordCounts
