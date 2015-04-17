def detect_anagrams(word, word_list):
	anagrams = []
	for possible_anagram in word_list:
		if possible_anagram.lower() != word.lower():
			if ''.join(sorted(word.lower())) == ''.join(
				sorted(possible_anagram.lower())):
				anagrams.append(possible_anagram)
	return anagrams
