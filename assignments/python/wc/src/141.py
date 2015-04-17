def word_count(expr):
	'''
	Function to count the number of occurances
	of each word in the input expression
	'''
	words = expr.split()
	dict = {}
	for i in range(0, len(words)):
		if i>0 and words[i] in words[0:i-1]:
			continue
		dict[words[i]] = words.count(words[i])
	return dict
