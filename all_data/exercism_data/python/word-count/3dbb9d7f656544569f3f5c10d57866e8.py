def word_count(expr):
	'''
	Function to count the number of occurances
	of each word in the input expression
	'''

	# set up a dictonary to hold the return information
	dict = {}

	# loop over words in expr seperated by whitespace
	for word in expr.split():
		if not word in dict:
			# add word to dictionary
			dict[word] = 1
		else:
			# increment the count of word
			dict[word] += 1

	return dict
