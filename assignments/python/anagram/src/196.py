def detect_anagrams(word, word_list):
	anagrams = []
	word = word.lower()
	for w in word_list:
		if w.lower() != word and not w in anagrams and len(w) == len(word):
			l = list(w.lower())
			for c in word:
				if c in l:
					i = l.index(c)
					l.pop(i)
			if not l:
				anagrams.append(w)
	return anagrams
