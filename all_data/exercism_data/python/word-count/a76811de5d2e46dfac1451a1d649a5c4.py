import re
def word_count(string):
	words = {}
	string = re.sub(r'[^\w ]+', '', string)
	array_of_words = re.split(r'\s*', string.lower());
	for word in array_of_words:
		if word in words:
			words[word] += 1
		else:
			words[word] = 1
	return words
