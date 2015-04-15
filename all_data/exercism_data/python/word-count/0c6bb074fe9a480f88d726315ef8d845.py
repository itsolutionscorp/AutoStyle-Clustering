import string
import re

class Phrase(object):
	def __init__(self, phrase):
		self.phrase = phrase

	def word_count(self):
		strippedPhrase = ""
		for char in self.phrase:
			if char not in string.punctuation:
				strippedPhrase = strippedPhrase + char
		strippedPhrase = re.sub(' +',' ', strippedPhrase)

		listofwords = strippedPhrase.split(' ')
		worddict = {}
		for word in listofwords:
			word = word.lower()
			if word not in worddict.keys():
				worddict[word] = 1
			else:
				worddict[word] += 1
		return worddict
