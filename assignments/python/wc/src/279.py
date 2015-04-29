import re
def word_count(strSentence):
	"""
	This functions generates a histogram with the occurences of each word in a phrase.
	"""
	strClean = strSentence.strip()
	listWords = re.split("\s*",  strClean )
	dictFreq = {}
	for strWord in listWords:
		if dictFreq.has_key(strWord):
			dictFreq[strWord] += 1
		else:
			dictFreq[strWord] = 1
	return dictFreq
