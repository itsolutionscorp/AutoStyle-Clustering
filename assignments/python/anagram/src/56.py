from collections import Counter
def detect_anagrams(word, lst):
	word = word.lower()
	c_word = Counter(word)
	return [
		i
		for i in lst
		if c_word == Counter(i.lower()) and word != i.lower()
	]
