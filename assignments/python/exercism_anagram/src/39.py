from collections import Counter
def detect_anagrams(word, lst):
	sorted_word = sorted(word.lower())
	return [
		i
		for i in lst
		if word != i and sorted_word == sorted(i.lower())
	]
