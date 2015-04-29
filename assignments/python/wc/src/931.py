def word_count(orig): 
	import string
	orig = orig.lower()
	s = orig.translate(string.maketrans("",""), string.punctuation)
	words = s.split() 
	unique_words = set(words)
	dict = {}
	for word in unique_words: 
		dict[word] = 0
	for n in words:
		for key in dict.keys(): 
			if key == n: 
				dict[key] = dict[key] + 1
	return dict
