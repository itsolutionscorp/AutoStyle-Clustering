def hey(statement):
	if statement.isupper():return 'Whoa, chill out!'
	if statement.endswith('?'): return 'Sure.'
	if statement.split()==[]: return 'Fine. Be that way!'
	return 'Whatever.'
	
