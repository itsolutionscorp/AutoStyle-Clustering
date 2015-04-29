def isyelling(some_string):
	return some_string.isupper()

def issilent(some_string):
	return not some_string or some_string.isspace()

def isquestion(some_string):
	return some_string.strip().endswith('?')

def hey(some_string):
	if issilent(some_string):
		return 'Fine. Be that way!'
	elif isyelling(some_string):
		return 'Whoa, chill out!'
	elif isquestion(some_string):
		return 'Sure.'
	else:
		return 'Whatever.'
