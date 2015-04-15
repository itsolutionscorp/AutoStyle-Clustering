def hey(expr):
	'''
	Basic call and response function
	'''

	# check for empty expression
	if len(expr.strip()) == 0:
		return "Fine. Be that way!"
	# check for 'yelling' (all caps)
	elif expr.isupper():
		return "Whoa, chill out!"
	# check for a question, '?' should be the last char
	elif expr[-1] == '?':
		return 'Sure.'
	# anything else
	else:
		return 'Whatever.'
