from collections import Counter

def word_count(phrase):
	wordCount = Counter()
	for word in phrase.split():
		wordCount[word]+= 1
	return wordCount		
