def hey(what):
	what = what.strip()
	ret = 'Whatever.'	
	if what.isupper():
		ret = 'Whoa, chill out!'
	elif len(what) == 0:
		ret = 'Fine. Be that way!'
	elif what[-1] == '?':
		ret = 'Sure.'
	return ret
