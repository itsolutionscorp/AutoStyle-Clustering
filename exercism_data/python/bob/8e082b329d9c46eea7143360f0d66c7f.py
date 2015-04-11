# bob.py

def hey(statement):

	statement.strip()
	
	answer = 'Whatever.'

	if (statement.endswith('?')):
		answer = 'Sure.'
	
	if (statement.isupper()):
		answer =  'Woah, chill out!'

	if (statement.isspace() or statement == ''):
		answer = 'Fine. Be that way!'

	return answer
