def detect_anagrams(word, words):
	anagrams = []
	# Sort all letters in the word in uppercase
	sortedword = ''.join(sorted(word.upper()))
	for test in words:
		# Test the already sorted word agains the elements of the list that are sorted in uppercase
		if sortedword == ''.join(sorted(test.upper())) and word.upper() != test.upper():
			anagrams.append(test)
	return anagrams
