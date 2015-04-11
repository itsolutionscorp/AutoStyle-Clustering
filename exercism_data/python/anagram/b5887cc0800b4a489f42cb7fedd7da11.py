# Anagram detector

def detect_anagrams(word, candidates):
	wordLetters = lettersCount(word)
	return [candidate for candidate in candidates \
				          if candidate.lower() != word \
			   	             and lettersCount(candidate) == wordLetters]

def lettersCount(word):
	letters = {}
	for letter in word.lower():
		letters[letter] = letters.get(letter, 0) + 1
	return letters
