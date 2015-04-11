def word_count(phrase):
	countDict = {}
	words = phrase.split()
	return countRecursive(countDict, words)

def countRecursive(countDict, wordList):
	if len(wordList) == 0:
		return countDict
	else :
		if not wordList[0] in countDict:
			countDict[wordList[0]] = 1;
		else:
			countDict[wordList[0]] = countDict[wordList[0]] + 1;
		
	return countRecursive(countDict,wordList[1:])
