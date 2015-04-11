def detect_anagrams(init_word, word_list):
	anagram_template = sorted(list(init_word.lower()))

	return [word for word in word_list 
			if sorted(list(word.lower())) == anagram_template 
			and word.lower() != init_word.lower()]
	
