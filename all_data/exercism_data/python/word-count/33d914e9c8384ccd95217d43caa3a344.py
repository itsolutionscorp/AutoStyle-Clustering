def word_count(expr):
	'''
	Function to count the number of occurances
	of each word in the input expression
	'''

	# set up a dictonary to hold the return information
	dict = {}

	# loop over words in expr seperated by whitespace
	for word in expr.split():
		# add to the dictionary with default value of 0
		# should not overwrite value if key exists
		dict.setdefault(word,0)

		#increment the count of word
		dict[word] += 1

	return dict
