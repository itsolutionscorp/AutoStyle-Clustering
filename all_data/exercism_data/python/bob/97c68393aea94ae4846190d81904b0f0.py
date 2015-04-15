def hey(phrase):
	sure = 'Sure.'
	woah = 'Woah, chill out!'
	fine = 'Fine. Be that way!'
	whatever = 'Whatever.'
	
	
	if phrase == phrase.upper():
		return woah
	elif phrase[-1:] == "?":
		return sure	
	else:
		return whatever
