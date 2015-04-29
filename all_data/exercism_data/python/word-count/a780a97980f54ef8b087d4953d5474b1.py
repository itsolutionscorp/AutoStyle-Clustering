# word_count.py
import string
def word_count(rawWords):
	countList = {}
	theWords = []
	theLines = rawWords.splitlines()
	for line in theLines:
		theWords.extend(line.split(' '))
	for word in theWords:
		if not word.isspace() and word:
			if word not in countList:
				countList[word] = 1
			else:
				countList[word] += 1
	return countList
