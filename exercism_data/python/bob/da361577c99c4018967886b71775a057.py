def hey(statement):
	if len(statement.strip()) == 0:
		return 'Fine. Be that way!'
	elif statement.upper() == statement and any(c.isalpha() for c in statement):
		return 'Whoa, chill out!'
	elif statement.endswith('?'):
		return 'Sure.'
	return 'Whatever.'
