def hey(question):

	if question == '' or question == '    \t':
		return ('Fine. Be that way!')
	elif question.isupper():
		return ('Whoa, chill out!')
	elif question.endswith('?'):
		return ('Sure.')
	else:
		return ('Whatever.')
