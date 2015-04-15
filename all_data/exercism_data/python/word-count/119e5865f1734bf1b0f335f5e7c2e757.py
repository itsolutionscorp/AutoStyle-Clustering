def word_count(string):
	words = string.split()
	wordMap = {}
	for word in words:
		if word in wordMap:
			wordMap[word] += 1
		else:
			wordMap[word] = 1
	return wordMap
