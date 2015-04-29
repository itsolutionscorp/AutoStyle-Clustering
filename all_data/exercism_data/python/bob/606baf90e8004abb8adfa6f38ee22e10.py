def hey(question):
	
	if question.isupper():
		return 'Whoa, chill out!'
	elif question.endswith('?'):
		return 'Sure.'
	elif not question.strip():
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
