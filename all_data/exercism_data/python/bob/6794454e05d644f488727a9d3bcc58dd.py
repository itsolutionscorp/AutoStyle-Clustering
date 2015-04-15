def hey(msg):
	if not msg.strip():
		return 'Fine. Be that way!'
	if msg == msg.upper() and msg != msg.lower():
		return 'Whoa, chill out!'
	if msg[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
