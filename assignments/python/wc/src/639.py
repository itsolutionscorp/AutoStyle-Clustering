def word_count(phrase):
	wordDict = {}
	for word in phrase.split():
		if word in wordDict:
			wordDict[word]+= 1 
		else:
			wordDict[word] = 1
	return wordDict		
