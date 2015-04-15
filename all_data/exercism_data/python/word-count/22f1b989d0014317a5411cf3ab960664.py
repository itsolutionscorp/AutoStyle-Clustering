from collections import Counter
import re

def word_count(input):
	input = input.lower()
	wordlist = re.sub("[^\w]", " ",  input).split()

	return Counter(wordlist)
