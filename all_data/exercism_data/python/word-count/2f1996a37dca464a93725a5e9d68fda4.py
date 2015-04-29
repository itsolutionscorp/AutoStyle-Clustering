import re

def word_count(strSentence):
	"""
	This functions generates a histogram with the occurences of each word in a phrase.
	"""
	# Removing extra whitespaces
	strClean = strSentence.strip()
	
	# Obtaining a list of words	
	listWords = re.split("\s*",  strClean )
	
	# Creating empty dict
	dictFreq = {}
	
	# Creating histogram
	for strWord in listWords:
		if dictFreq.has_key(strWord):
			dictFreq[strWord] += 1
		else:
			dictFreq[strWord] = 1
	
	return dictFreq
