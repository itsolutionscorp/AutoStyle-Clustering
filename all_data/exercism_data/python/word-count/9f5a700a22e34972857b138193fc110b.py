#counts how many of each word are in a string
#ignores punctuation and case

def word_count(orig): 
	import string
	orig = orig.lower()
	s = orig.translate(string.maketrans("",""), string.punctuation)
	words = s.split() 
	# set of words in string
	unique_words = set(words)
	dict = {}
		
	
	#make dictionary of all the words
	for word in unique_words: 
		dict[word] = 0
	
	# now go through the original string
	# when the word comes up, increase that word's counter in dict
	for n in words:
		for key in dict.keys(): 
			if key == n: 
				dict[key] = dict[key] + 1
	
	return dict
