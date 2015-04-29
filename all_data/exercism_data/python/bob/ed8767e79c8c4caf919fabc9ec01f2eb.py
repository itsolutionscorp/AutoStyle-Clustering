def hey(phrase):
	phrase = phrase.strip()
	if len(phrase) == 0:
		return 'Fine. Be that way!'
	elif phrase.isupper():
		return 'Woah, chill out!'
	elif phrase[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
