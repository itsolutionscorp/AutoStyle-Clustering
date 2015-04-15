import re

def word_count(string):
	words_dict = {}
	words_array = re.findall('\S+', string)
	for word in words_array:
		if word in words_dict:
			words_dict[word] += 1
		else:
			words_dict[word] = 1
	return words_dict
