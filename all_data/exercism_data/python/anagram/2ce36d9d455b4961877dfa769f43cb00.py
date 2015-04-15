def is_anagram_of(word1, word2):
	caps1, caps2 = word1.upper(), word2.upper()
	return sorted(caps1) == sorted(caps2) and caps1 != caps2

def detect_anagrams(word, words):
	return list(filter(lambda s: is_anagram_of(s, word), words))
