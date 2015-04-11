def hey(statement):

	if statement.strip(' \t\n\r') == '':
		return 'Fine. Be that way!'
	
	if statement.isupper():
		return 'Whoa, chill out!'
	
	if statement.endswith('?'):
		return 'Sure.'
	
	return 'Whatever.'
