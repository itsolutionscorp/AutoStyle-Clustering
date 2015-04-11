def detect_anagrams(word, options):
	results = []
	for option in options:
		if len(word) == len(option) and word.lower() != option.lower():
			if is_anagram(word.lower(), option.lower()):
				results.append(option)
	return results
		
def is_anagram(word, anagram):
	word_letters = list(word)	
	for letter in anagram:
		if letter not in word_letters:
			return False
			break
		else:
			word_letters.remove(letter)
	else:
		return True
