import string
def word_count(phrase):
	wordDict = {}
	wordList = phrase.translate(None, string.punctuation).lower().split()
	while len(wordList)>0:
		if wordList[0] in wordDict:
			wordDict[wordList[0]] += 1
		else:
			wordDict[wordList[0]] = 1
		wordList.pop(0)
	return wordDict
