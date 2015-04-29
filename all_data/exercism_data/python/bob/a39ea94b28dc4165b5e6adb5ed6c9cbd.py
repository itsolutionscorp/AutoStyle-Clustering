def hey(statement):
	statementType = typeOfStatement(statement)
	
	if statementType == 'yelling':
		return 'Whoa, chill out!'
	elif statementType == 'question':
		return 'Sure.'
	elif statementType == 'silence':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'

def typeOfStatement(statement):
	'''
	Based on the testing comparisons, yelling overrides the question in the 
	case of yelling a question. For example "IS THIS A QUESTION?"
	'''
	statementType = 'none'
	
	if statement.upper() == statement and statement.lower() != statement:
		return 'yelling'
	elif statement.endswith('?'):
		return 'question'
	elif statement.strip() == '':
		return 'silence'
	else:
		return 'other'
		
