def hey(txt):
	if txt.strip() == '':
		return u'Fine. Be that way!'
	elif txt.upper() == txt and txt.lower() != txt:
		return u'Whoa, chill out!'
	elif txt[-1] == '?':
		return u'Sure.'
	else:
		return u'Whatever.'
