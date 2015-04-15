def hey(talkString):
	try:
		isInstance(talkString, str)
	except Exception, e:
		print 'The value is not a string.'
	if talkString.isupper():
		return 'Whoa, chill out!'
	elif talkString[-1:] == '?':
		return 'Sure.'
	elif talkString.strip() == '':
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'
