import re
def word_count(inputString):
	dictionary = dict()
	for word in re.findall(r"[\w']+", inputString):
		word = word.lower()
		if word in dictionary: 
			dictionary[word] = dictionary[word]+1
		else:
			dictionary[word] = 1
	return dictionary
