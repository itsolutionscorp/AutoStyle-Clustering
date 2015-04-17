import string
def word_count(phrase):
	wordDict = {}
	phrase = phrase.translate(None, string.punctuation).lower()
	wordList = phrase.split()
	while len(wordList)>0:
		wordToAnalyze = wordList[0]
		if wordToAnalyze in wordDict:
			wordDict[wordToAnalyze] += 1
		else:
			wordDict[wordToAnalyze] = 1
		wordList.pop(0)
	return wordDict
