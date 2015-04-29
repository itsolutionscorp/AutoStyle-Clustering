def detect_anagrams(word, check_list):
	result = []
	for single_word in check_list:
		if word.lower() != single_word.lower() and sorted(word.lower()) == sorted(single_word.lower()):
			result.append(single_word)
	return result
