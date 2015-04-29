def detect_anagrams(anagram, list_of_words):
	
	possible = []
	
	for word in list_of_words:
		if is_anagram(anagram, word):
			possible.append(word)
			
	return possible


def is_anagram(anagram, word, case_sensitive=False):
	
	if not case_sensitive:
		anagram = anagram.lower()
		word = word.lower()
	
	if anagram == word or len(anagram) != len(word):
		return False
	
	anagram = list(anagram)
	word = list(word)
	
	for letter in word:
		if letter in anagram:
			anagram.remove(letter)
		else:
			return False
	
	return True
			
