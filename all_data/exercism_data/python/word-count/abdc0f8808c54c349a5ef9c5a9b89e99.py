#
# word-count exercise
#
import re
def word_count(phrase):

	wordlist = phrase.split()
	
	#list of words that appear more than once
	repeated_words = []
	
	#output in dictionary format to past the test
	output = {}

	for x in wordlist :
		if x not in repeated_words : # check to output each word only once
			count = len(re.findall(r'\b%s\b' % x, phrase))
			output [x] = count
			if count > 1 :
				repeated_words.append(x)

	return output
