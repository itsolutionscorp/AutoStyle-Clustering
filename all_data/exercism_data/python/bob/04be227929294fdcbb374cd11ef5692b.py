def hey(msg):
	response = 'Whatever.'
	msg = msg.strip() # remove whitespace

	if msg == '':
		response = 'Fine. Be that way!'
	elif msg.isupper():
		response = 'Whoa, chill out!'
	elif msg[-1] == '?':
		response = 'Sure.'
	return response
