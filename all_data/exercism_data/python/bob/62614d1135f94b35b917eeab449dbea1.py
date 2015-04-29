def hey(what):
	question	= 'Sure.'
	yelling		= 'Whoa, chill out!'
	nothing		= 'Fine. Be that way!'
	default		= 'Whatever.'

	response 	= what.rstrip().expandtabs()

	# After stripping the string, if it's empty, you have nothing.
	if not response:
		return nothing

	if response.isupper():
		return yelling
	elif response[-1] == '?':
		return question
	else:
		return default
