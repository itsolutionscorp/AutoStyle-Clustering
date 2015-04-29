import string
def word_count(s):
	dct = {}
	s = s.translate(string.maketrans("",""), string.punctuation)
	for word in s.split():
	    if word not in string.punctuation:
			dct[word.lower()] = dct.get(word.lower(), 0) + 1 
	return dct
