from collections import Counter
def detect_anagrams(word, words):
	anagrams = []
	word = word.lower()
	sortedWord = ''.join(sorted(word))
	for w in words:
		lw = w.lower()
		if lw != word and sortedWord == ''.join(sorted(lw)):
			anagrams.append(w)
	return anagrams
