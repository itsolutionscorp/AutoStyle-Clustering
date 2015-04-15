def detect_anagrams(word, tests):
	word = word.lower()
	#ntests = [x.lower() for x in tests]
	test_list = [x for x in tests if (len(word) == len(x)) and (word != x.lower())]
	
	anagrams =[x for x in test_list if sorted(list(word)) == sorted(list(x.lower()))]
	

	return anagrams
