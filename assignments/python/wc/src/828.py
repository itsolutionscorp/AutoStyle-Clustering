wordDict = {}
def word_count(str):
	for word in str.split():
		if word not in wordDict:
			wordDict[word] = 1
		else: # in wordDict
			wordDict[word] += 1
