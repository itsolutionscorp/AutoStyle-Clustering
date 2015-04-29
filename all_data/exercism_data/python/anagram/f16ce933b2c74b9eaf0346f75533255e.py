def detect_anagrams(word, lst):
	sorted_word = sorted(word.lower())
	return [
		i
		for i in lst
		if word.lower() != i.lower() and sorted_word == sorted(i.lower())
	]
