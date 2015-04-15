import re

def word_count(phrase):
	regex = re.compile("\s+")
	words = regex.split(phrase)
	return dict([[x, words.count(x)] for x in set(words)])
