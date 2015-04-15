from collections import Counter

def detect_anagrams(word, words):
	anagrams = []
	word = word.lower()
	for w in words:
		if w.lower() != word and Counter(w.lower()) == Counter(word):
			anagrams.append(w)
	return anagrams
