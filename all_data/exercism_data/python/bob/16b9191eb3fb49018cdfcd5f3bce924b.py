def hey(input):
 
	if input.isupper():
		return 'Whoa, chill out!'
	elif input.isspace() or input == '':
		return 'Fine. Be that way!'
	elif input[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
