def hey(saywhat):
	if saywhat.isupper():
		return 'Whoa, chill out!'
	elif saywhat.endswith('?'):
		return 'Sure.'
	elif saywhat.isspace() or len(saywhat) == 0:
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
