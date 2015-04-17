def detect_anagrams(word, list_of_words):
	anagrams = []
	word = word.lower()
	sorted_word = ''.join(sorted(word))
	
	for string in list_of_words:
		if string.lower() != word and ''.join(sorted(string.lower())) == sorted_word:
			anagrams.append(string)
	return anagrams
