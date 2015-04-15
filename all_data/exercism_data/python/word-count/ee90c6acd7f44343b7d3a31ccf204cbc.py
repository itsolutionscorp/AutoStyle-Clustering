import re

def word_count(text):
	words = filter(None, re.sub('[^A-Za-z0-9\s]+', '', text).split(" "))
	wordCount = {}

	for word in words:
		word = word.lower()
		if word in wordCount:
			wordCount[word] = wordCount[word] + 1
		else:
			wordCount[word] = 1

	return wordCount
