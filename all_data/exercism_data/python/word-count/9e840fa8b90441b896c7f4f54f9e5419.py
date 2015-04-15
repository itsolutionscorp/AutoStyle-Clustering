# Returns a dictionary containing
# each unique word specified in [words]
# and the number of occurrences of that word
def word_count(words):
	wordDict = {}
	
	for word in words.split():
		wordDict[word] = wordDict.get(word,0) + 1
	
	return wordDict	
