import re
from collections import Counter

def split(phrase):
	return re.split(r"[ \n]", phrase)

def remove(str_list):
	#remove empty string from list
	return filter(None, str_list)

def trim(str_list):
	return map(str.strip, str_list)

def word_count(phrase):
	
	all_words = remove(trim(split(phrase)))

	return Counter(all_words)
