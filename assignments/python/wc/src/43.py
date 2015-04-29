def word_count(phrase):
	wordcount = {}
	words = phrase.split()
	for word in words:
		if word not in wordcount:
			wordcount[word] = 1
		else:
			wordcount[word] += 1
	return wordcount
