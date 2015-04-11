def hey(call):
	if call.strip() == '':
		return 'Fine. Be that way!'
	endchar = call[-1]
	if call.upper() == call and call.upper() != call.lower():
		return 'Whoa, chill out!'
	elif endchar == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
