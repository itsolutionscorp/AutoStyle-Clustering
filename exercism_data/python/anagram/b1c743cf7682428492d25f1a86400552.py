def detect_anagrams(word, l):
	word_letters = list(word.lower())
	word_letters.sort()
	words_list = [list(i.lower()) for i in l]
	anagrams = []
	for w in range(len(words_list)):
		words_list[w].sort()
		print(words_list[w] == word_letters)
		if word.lower() != l[w].lower() and words_list[w] == word_letters:
			anagrams.append(l[w])
	return anagrams
