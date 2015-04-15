import string

def word_count(str):
	dct = {}
	str = str.translate(string.maketrans("",""), string.punctuation)
	for word in str.split():
	    if word not in string.punctuation:
			dct[word.lower()] = dct.get(word.lower(), 0) + 1 
	return dct
