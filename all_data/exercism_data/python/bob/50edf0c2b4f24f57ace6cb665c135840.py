def hey(what):
	what_rstripped = what.rstrip()
	if (what_rstripped.isupper()):
		return 'Whoa, chill out!'
	elif (what_rstripped.endswith('?')):
		return 'Sure.'
	elif not what_rstripped or what_rstripped.isspace():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
