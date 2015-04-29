def hey(s):
	if ''==s.strip():
		return 'Fine. Be that way!'
	elif s.isupper():
		return 'Woah, chill out!'
	elif '?'==s[-1:]:
		return 'Sure.'
	else:
		return 'Whatever.'
