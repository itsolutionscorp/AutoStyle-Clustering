def hey(what):

	# if string is empty or completely whitespace
	if what.isspace() or len(what) < 1:
		return 'Fine. Be that way!'

	# if all letters are uppercase
	elif what.isupper():
			return 'Whoa, chill out!'

  # if string ends in '?'
	elif what.endswith('?'):
			return 'Sure.'

	# all other options
	return 'Whatever.'
