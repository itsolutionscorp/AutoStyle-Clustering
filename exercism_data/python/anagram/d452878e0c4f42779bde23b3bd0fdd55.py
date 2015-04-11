def is_anagram(word_lower, candidate, cached_sorted_word_lower=None):
	"""Pass in `cached_sorted_word_lower` for sjakobi speed boost. :)"""
	candidate_lower = candidate.lower()
	sorted_word_lower = cached_sorted_word_lower or sorted(word_lower)
	return word_lower != candidate_lower and sorted_word_lower == sorted(candidate_lower)

def detect_anagrams(word, candidate_list):
	word_lower = word.lower()
	return [
		candidate for candidate in candidate_list
		if is_anagram(word_lower, candidate, cached_sorted_word_lower=sorted(word_lower))
	]
