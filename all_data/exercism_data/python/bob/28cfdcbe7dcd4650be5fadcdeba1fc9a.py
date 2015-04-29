def hey(question):
	if question.strip() == '':
		return 'Fine. Be that way!'
	elif question.upper() == question and not question.upper() == question.lower():
		return 'Whoa, chill out!'
	elif question[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
