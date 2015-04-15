from collections import Counter

def word_count(my_words):
	wordList = my_words.split()
	counter = Counter(wordList)
	return counter
