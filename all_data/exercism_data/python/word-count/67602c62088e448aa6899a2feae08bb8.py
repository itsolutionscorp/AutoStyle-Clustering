#
# word-count exercise
# iter. 2
#

def word_count(phrase):

	wordlist = phrase.split()
	
	#output in dictionary format to past the test
	output = {}

	for x in wordlist :
		if x not in output : 
			output [x] = 1
		else : output [x] += 1

	return output
