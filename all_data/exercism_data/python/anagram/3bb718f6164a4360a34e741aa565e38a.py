def detect_anagrams(word, candidate_list):
	word_lower = word.lower()
	sorted_word_lower = sorted(word_lower)
	def is_anagram(candidate):
		candidate_lower = candidate.lower()
		return word_lower != candidate_lower and sorted_word_lower == sorted(candidate_lower)
	return filter(is_anagram, candidate_list)
