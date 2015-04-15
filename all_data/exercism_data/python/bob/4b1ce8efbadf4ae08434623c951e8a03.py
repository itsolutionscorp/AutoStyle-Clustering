def hey(question):
	"""
	Inputs:
		question (string) - A question to ask Bob.
	Outputs:
		answer (string) - Bob's response to the question.
		
	Bob answers 'Sure.' if you ask him a question.
	He answers 'Whoa, chill out!' if you yell at him.
	He says 'Fine. Be that way!' if you address him without actually saying anything.
	He answers 'Whatever.' to anything else.
	"""
	if (question.strip() == ''):
		answer = 'Fine. Be that way!'
	elif (question.isupper()):
		answer = 'Whoa, chill out!'
	elif (question[-1] == '?'):
		answer = 'Sure.'
	else:
		answer = 'Whatever.'
	return answer
	
	
