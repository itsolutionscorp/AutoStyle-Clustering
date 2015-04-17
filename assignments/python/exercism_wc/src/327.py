def word_count(words):
	from collections import Counter
	wordlist = words.split()
	return Counter(wordlist)
