def hey(say):
	answer = ''

	if say.isupper():
		answer = 'Whoa, chill out!'
	elif say[-1:] == '?':
		answer = 'Sure.'
	elif say.isspace() or not say:
		answer = 'Fine. Be that way!'
	else:
		answer = 'Whatever.'

	return answer
