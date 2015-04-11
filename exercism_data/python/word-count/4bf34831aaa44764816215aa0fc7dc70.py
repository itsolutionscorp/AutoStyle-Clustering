#Program file for word_count_test.py
from collections import Counter

#Uses the Counter module from collections to solve the problem
def word_count(sentence):
	words = Counter(sentence.split())
	return words
	
