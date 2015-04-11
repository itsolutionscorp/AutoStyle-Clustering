def word_count(s):
	words = s.split()
	wordDict = {}
	for word in words:
		if word not in wordDict:
			wordDict[word] = 1
		else:
			wordDict[word] = wordDict.get(word) + 1
	return wordDict
