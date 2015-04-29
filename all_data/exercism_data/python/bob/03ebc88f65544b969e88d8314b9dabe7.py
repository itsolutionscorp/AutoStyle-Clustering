def hey(statement):
	if statement.endswith('?') and statement.isupper():
		return 'Whoa, chill out!'
	elif statement.endswith('?'):
		return 'Sure.'
	elif statement.isupper():
		return "Whoa, chill out!"
	elif statement == '' or statement.isspace():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
	
