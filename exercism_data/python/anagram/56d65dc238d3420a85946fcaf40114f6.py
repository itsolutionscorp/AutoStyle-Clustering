def is_anagram_of(w1, w2):
	caps1 = w1.upper()
	caps2 = w2.upper()
	sort1 = ''.join(sorted(caps1))
	sort2 = ''.join(sorted(caps2))
	return sort1 == sort2 and caps1 != caps2

def detect_anagrams(word, words):
	return list(filter(lambda s: is_anagram_of(s, word), words))
