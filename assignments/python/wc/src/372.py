def word_count(expr):
	'''
	Function to count the number of occurances
	of each word in the input expression
	'''
	dict = {}
	for word in expr.split():
		if not word in dict:
			dict[word] = 1
		else:
			dict[word] += 1
	return dict
