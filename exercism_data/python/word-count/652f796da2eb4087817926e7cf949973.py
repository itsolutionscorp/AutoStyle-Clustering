def word_count(phrase):
	ocur = {}
	wordList = phrase.split();
	for w in wordList:
		w = ''.join(e for e in w if e.isalnum())
		w = w.lower().strip()
		if not w == '':
			if not w in ocur:
				ocur[w] = 1
			else:
				ocur[w] += 1
	print wordList
	return ocur
