from collections import Counter

def detect_anagrams(word, words):
	anagrams = []
	word = word.lower()
	for w in words:
		if Counter(w.lower()) == Counter(word) and w.lower() != word:
			anagrams.append(w)
	return anagrams
