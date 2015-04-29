def hey(phrase):
	sure = 'Sure.'
	woah = 'Woah, chill out!'
	fine = 'Fine. Be that way!'
	whatever = 'Whatever.'
	
	
	if phrase.isupper():
		return woah
	elif phrase[-1:] == '?':
		return sure	
	elif phrase.isspace() or phrase == '':
		return fine
	else:
		return whatever
