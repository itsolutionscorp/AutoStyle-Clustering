import string
def word_count(phrase):
	returnDict={}
	for word in phrase.split():
		word=word.strip(' \n\r\t'+string.punctuation)
		word=word.lower()
		if len(word)==0:
			continue
		if word in returnDict.keys():
			returnDict[word] = returnDict[word] + 1
		else:
			returnDict[word]=1
	return returnDict
