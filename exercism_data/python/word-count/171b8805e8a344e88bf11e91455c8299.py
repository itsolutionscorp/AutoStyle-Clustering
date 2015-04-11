def word_count(expr):
	'''
	Function to count the number of occurances
	of each word in the input expression
	'''

	# split input into words seperated by whitespace
	words = expr.split()

	# set up a dictonary to hold the return information
	dict = {}

	# count the number of occurrances of each word
	for i in range(0, len(words)):
		# unless it occured earlier in the list
		if i>0 and words[i] in words[0:i-1]:
			continue

		dict[words[i]] = words.count(words[i])

	return dict
