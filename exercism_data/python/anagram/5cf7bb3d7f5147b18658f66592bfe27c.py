def detect_anagrams(word, wordlist):
	anagrams = []
	
	for w in wordlist:
		temp_word = word.lower()
		temp_w = w.lower()
		if temp_w != temp_word:
			for c in temp_w:
				
				if c in temp_word:
					temp_word = temp_word.replace(c,'',1)
					temp_w = temp_w.replace(c,'',1)
					

				if temp_w == '' and temp_word == '':
					anagrams.append(w)
		
	return anagrams
