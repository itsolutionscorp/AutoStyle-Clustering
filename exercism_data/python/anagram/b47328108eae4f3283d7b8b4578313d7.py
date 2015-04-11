def detect_anagrams(word, candidates):
	result = []
	
	for w in candidates:
		w_list = list(w.lower())
		isAnagram = True
		
		# remove each letter of the word from the candidate
		for l in word.lower():
			try:
				w_list.remove(l)
			# if a letter does not exists in candidate word then it is not an anagram
			except ValueError:
				isAnagram = False
				break
				
		# if there are any letters remaining then it is not an anagram
		if len(w_list) != 0:
			isAnagram = False
		
		# if it is the sam word it is not an anagram
		if word.lower() == w.lower():
			isAnagram = False
		
		# if it passed all the tests append it to the list
		if isAnagram:
			result.append(w)

	return result
