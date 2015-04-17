def word_count(sentence):
	wordCount = {}
	words = sentence.split()
	for word in words:
		if word not in wordCount:
			wordCount[word] = 0
		wordCount[word] = wordCount[word] + 1
	return wordCount
