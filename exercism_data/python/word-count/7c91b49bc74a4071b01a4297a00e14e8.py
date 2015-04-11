#Import a Counter module from the library
from collections import Counter

def word_count(sentence):
	#This line splits the string and then counts each occurrence of a word
	return Counter(sentence.split())
