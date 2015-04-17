from collections import Counter
def detect_anagrams(word, possible_words):
	letter_count = Counter(list(word.lower()))
	anagrams = []
	for w in possible_words:
		if not word.lower() == w.lower() and Counter(list(w.lower())) == letter_count:
			anagrams.append(w)
	return anagrams
