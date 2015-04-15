def detect_anagrams(anagram_base, compare_string_list):
	sorted_base = sorted(anagram_base.lower())
	return_matches = []
	for word in compare_string_list:
		if (sorted(word.lower()) == sorted_base) and (word.lower() != anagram_base.lower()):
			return_matches.append(word)

	return return_matches
