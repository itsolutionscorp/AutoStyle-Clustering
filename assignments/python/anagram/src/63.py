def detect_anagrams(input_keyword, anagram_candidates):
	#format input_keyword once per method call.
	lower_input_keyword = input_keyword.lower()
	sorted_lower_input_keyword = sorted(list(lower_input_keyword))
	#declare list of anagrams to be returned
	anagrams = []
	for potential_anagram in anagram_candidates:
		lower_potential_anagram = potential_anagram.lower()
		#make sure they are not the same word, but ARE the same length
		if lower_potential_anagram != lower_input_keyword and len(lower_potential_anagram) == len(lower_input_keyword):
			#compare sorted lists of letters from both anagram candidates and input_keyword to find anagrams
			if not sum(a != b for a, b in zip(sorted(list(lower_potential_anagram)), sorted_lower_input_keyword)):
				anagrams.append(potential_anagram)
	return anagrams
