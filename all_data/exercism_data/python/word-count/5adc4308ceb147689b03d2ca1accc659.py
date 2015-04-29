from collections import Counter

def word_count(string):
	string = string.split()
	x = Counter(string)
	return x
	
