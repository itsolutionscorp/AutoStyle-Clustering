def detect_anagrams(word, words):
	"""
	Input:	String word, which determines what the 
			anagrams will be spelled from.
			List words, which gives an unsorted list
			of words.
	Output:	List result, which gives words which are
			anagrams of the original word.
	"""
	result = []
	for i in range(0, len(words)):
		valid = True
		compareList1 = list(word.lower())
		compareList1.sort()
		compareList2 = list(words[i].lower())
		compareList2.sort()
		for a in range(0, len(word)):
			if compareList1[a] != compareList2[a] or len(compareList1) != len(compareList2):
				valid = False
				break
		if valid == True and words[i].lower() != word.lower():
			result.append(words[i])
	return result
