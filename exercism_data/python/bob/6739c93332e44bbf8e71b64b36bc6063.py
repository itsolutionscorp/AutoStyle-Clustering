def hey(statement):
	if statement.strip() == '':
		return 'Fine. Be that way!'
	elif statement.isupper():
		return 'Whoa, chill out!'
	elif statement[-1] == '?':
		return 'Sure.'
	return 'Whatever.'