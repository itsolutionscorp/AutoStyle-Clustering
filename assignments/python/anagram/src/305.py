def detect_anagrams(word, word_list):
	anagram_match = sorted(word.upper())
	possible_anagram =  [x for x in word_list 
		if sorted(x.upper()) == anagram_match 
		and x.upper() != word.upper()]
	return possible_anagram	
	
