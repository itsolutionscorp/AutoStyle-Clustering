import collections

def letterCount(checkword):
	iwLetterCount = collections.defaultdict(list)
	for l in checkword.upper():
		if l not in iwLetterCount:
			iwLetterCount[l] = 1
		else:
			iwLetterCount[l] += 1
	return iwLetterCount

def detect_anagrams(inWord,checkList):
	inWordCount = letterCount(inWord)
	returnList = []
	for listWord in checkList:
		wordCount = letterCount(listWord)
		if wordCount == inWordCount and inWord.upper() != listWord.upper():
			returnList.append(listWord)
	return returnList
