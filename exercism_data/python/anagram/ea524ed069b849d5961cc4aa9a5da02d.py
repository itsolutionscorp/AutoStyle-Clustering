def is_anagram(word_1, word_2):
	s1, s2 = word_1.lower(), word_2.lower()
	return s1 != s2 and sorted(s1) == sorted(s2)

def detect_anagrams(word, candidate_list):
	return [candidate for candidate in candidate_list if is_anagram(word, candidate)]
