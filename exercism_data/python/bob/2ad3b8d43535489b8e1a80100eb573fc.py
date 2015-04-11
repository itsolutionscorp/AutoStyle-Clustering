def hey(what):
	what = what.strip()
	if any(char.isalnum() for char in what):	
		if what.isupper():
			return 'Whoa, chill out!'
		if what.endswith('?'):
			return 'Sure.'
		return 'Whatever.'
	return 'Fine. Be that way!'
