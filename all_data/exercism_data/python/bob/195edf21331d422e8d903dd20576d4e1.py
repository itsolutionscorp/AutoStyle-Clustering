def hey(convoStart):
	if convoStart.isupper():
		return 'Whoa, chill out!'

	elif convoStart.endswith('?'):
		return 'Sure.'

	elif not convoStart.strip():
		return 'Fine. Be that way!'

	else:
		return 'Whatever.'
