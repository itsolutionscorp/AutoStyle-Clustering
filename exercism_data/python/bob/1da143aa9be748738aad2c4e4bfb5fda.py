def hey(msg):
	msg = msg.strip()
	msg = msg.replace(',', '')
	if (len(msg) == 0):
		return 'Fine. Be that way!'
	if(not msg.isdigit()):
		print "msg = " + msg
		allCaps = True
		for x in xrange(0, len(msg)):
			if (msg[x].isalpha() and msg[x].islower()):
				allCaps = False
				break
		if (allCaps == True):
			return 'Woah, chill out!'
	if (msg[len(msg)-1] == '?'):
		return 'Sure.'
	return 'Whatever.'
