def hey(comment):
	if comment.isupper():
		return 'Whoa, chill out!'
	elif comment.endswith('?'):
		return 'Sure.'
	elif not comment or comment.isspace():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
