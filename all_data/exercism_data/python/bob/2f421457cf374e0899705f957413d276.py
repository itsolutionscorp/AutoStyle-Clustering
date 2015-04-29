def hey(heystring=''):
	heystring = heystring.rstrip();
	if (heystring == ""):
		return 'Fine. Be that way!'
	if (heystring.isupper()):
		return 'Whoa, chill out!'
	elif (heystring.endswith("?")):
		return 'Sure.'
	else:
		return 'Whatever.'
