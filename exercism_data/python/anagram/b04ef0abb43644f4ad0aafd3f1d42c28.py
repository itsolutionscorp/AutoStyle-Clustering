"""This function receives a word and returns a list of anagrams from a list of potential candidates"""

def detect_anagrams(givenWord, psblAnagrams):
	anagrams = []
	givenIndex = index_chars(givenWord)

	for i in psblAnagrams:
		if index_chars(i) == givenIndex and \
		i.lower() != givenWord.lower():
			anagrams.append(i)
	return anagrams

def index_chars(word):
	charList = []
	charIndex = {}

	for i in word:
		charList.append(i.lower())

	for i in charList:
		charIndex[i] = charList.count(i)
	
	return charIndex
