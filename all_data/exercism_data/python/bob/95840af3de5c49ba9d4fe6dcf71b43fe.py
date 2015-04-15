def hey(what):
	if what.isupper():
		return 'Whoa, chill out!'

	elif what.isspace() or not what:
		return 'Fine. Be that way!'

	elif what[len(what) - 1] == '?':
		return 'Sure.'
		
	return 'Whatever.'
