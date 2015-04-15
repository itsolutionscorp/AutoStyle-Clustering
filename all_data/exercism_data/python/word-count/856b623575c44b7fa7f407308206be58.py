from collections import Counter

def word_count(phrase):
	counts = Counter(phrase.replace('\n',' ').split(' '))
	del counts['']
	
	return counts
