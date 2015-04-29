def detect_anagrams(target, sentence):
	return [ word for word in sentence if is_anagram(target, word)]

def is_anagram(target, word):
	""" Using ord(string) to convert strings to ascil integer encodings
		Anagrams will have the same sum 
	"""
	target = target.lower()
	word = word.lower()
	
	if len(target)!=len(word): 
		return False  
	elif target == word: # anagrams are not the same word
		return False   
	else:
		target_sum = sum([ord(w) for w in target])
		test_sum = sum([ord(w) for w in word])
		return target_sum == test_sum

def is_anagram2(target, word):
	""" Iteratively loop thru target character to look for 
	a match """

	target = target.lower()
	word = word.lower()

	if len(target)!=len(word):
		return False
	elif target == word:
		return False

	for c in target:
		pos = word.find(c)
		if ~pos:
			word = word[:pos] + word[pos+1:]
		else:
			return False
	return True
