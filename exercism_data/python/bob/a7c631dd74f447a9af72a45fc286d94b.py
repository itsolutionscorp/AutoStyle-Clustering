def hey(what):
	if what[0:len(what)-1].isupper() and not what[-1].islower():
		return 'Whoa, chill out!'
	elif what.endswith('?'):
		return 'Sure.'
	elif not what or '\t' in what:
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
