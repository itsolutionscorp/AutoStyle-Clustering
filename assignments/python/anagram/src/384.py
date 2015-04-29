#finds anagrams of test word from a list of words
def detect_anagrams(test_word, list_of_words):
	test_word = test_word.lower()
	print test_word
	list_of_anagrams = []
	for word in list_of_words:
		word_holder = word
		word = word.lower()
		if test_word == word: pass
		else:
			for letter in test_word:
				if letter in word:
					pos = word.index(letter)
					word = word[:pos]+word[pos+1:]
				else: break
			else:
				if not word: list_of_anagrams.append(word_holder)
	return list_of_anagrams
