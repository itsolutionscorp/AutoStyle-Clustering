def detect_anagrams(word, possible_anagrams):
	""" This function takes a word and a list (possible_anagrams) and see if the words in the list
	are anagrams of the first word. It then returns a list of those that are."""
	
	anagram = [] 
	for possible in possible_anagrams:
		if word != possible.lower():
			if sorted(word.lower()) == sorted(possible.lower()):
				anagram.append(possible)

	return anagram
