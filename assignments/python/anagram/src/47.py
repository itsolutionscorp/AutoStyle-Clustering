from collections import OrderedDict
def detect_anagrams(word, word_list):
	'''
	Given a word and a list of possible anagrams, selects the sublist of correct anagrams
	'''
	print(word_list)
	anagrams = []
	wordchars = {}
	for ch in word.lower():
		wordchars[ch] = wordchars.get(ch,0) + 1
	for listword in word_list:
		listchars = {}
		for ch in listword.lower():
			listchars[ch] = listchars.get(ch,0) + 1
		if listchars == wordchars:
			anagrams.append(listword)
	# remove the initial word, if it's there (we only want anagrams)
	anagrams = [a for a in anagrams if a.lower() != word.lower()]
	# remove duplicates, preserving order
	anagrams = list(OrderedDict.fromkeys(anagrams))
	return anagrams
