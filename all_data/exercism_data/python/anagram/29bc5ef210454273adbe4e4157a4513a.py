def detect_anagrams(word,word_list):
	anagrams = []
	for pos_word in word_list:
		if (len(word) == len(pos_word)):
			wl = word.lower()
			pwl = pos_word.lower()
			temp = [(wl[i] in pwl) and (wl.count(wl[i]) == pwl.count(wl[i])) for i in range(len(word))]
			
			if (temp.count(True) == len(word)) and (word.lower() != pos_word.lower()):
				anagrams.append(pos_word)
	return anagrams
