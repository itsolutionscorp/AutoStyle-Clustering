# wordcount.py
import string

def word_count(phrase):
	returnDict={}
	for word in phrase.split():
		# split by spaces
		word=word.strip(' \n\r\t'+string.punctuation)
		# strip whitespace and punctuation
		word=word.lower()
		# lowercase everything
		if len(word)==0:
			# if there is nothing left, continue on to next case
			continue
		if word in returnDict.keys():
			# add word to count
			returnDict[word] = returnDict[word] + 1
		else:
			# add word to dictionary
			returnDict[word]=1
	return returnDict
