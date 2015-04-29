
def detect_anagrams(givenWord, psblAnagrams):
	"""This function receives a word and returns a list of anagrams from a list of potential candidates"""
	anagrams = []
	givenIndex = index_chars(givenWord)

	for i in psblAnagrams:
		if index_chars(i) == givenIndex and \
		i.lower() != givenWord.lower():
			anagrams.append(i)
	return anagrams

def index_chars(word):
	"""This function returns a list of each of the characters that appear in a string and returns an index of their value and count."""
	charList = []
	charIndex = {}

	for i in word:
		charList.append(i.lower())

	for i in charList:
		charIndex[i] = charList.count(i)
	
	return charIndex
