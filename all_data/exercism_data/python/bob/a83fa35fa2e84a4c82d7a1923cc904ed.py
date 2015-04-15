def hey(string):
	if not string.strip(): return 'Fine. Be that way!'
	elif string.isupper(): return 'Whoa, chill out!'
	elif string.endswith('?'): return 'Sure.'
	return 'Whatever.'
