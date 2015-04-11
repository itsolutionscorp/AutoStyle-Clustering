# BOB

def hey(phrase):
	if len(phrase.rstrip(' \t\n\r'))==0:
		return 'Fine. Be that way!'
	if phrase.upper()==phrase and any(c.isalpha() for c in phrase):
		return 'Woah, chill out!'
	if phrase.endswith('?'):
		return 'Sure.'
	#if phrase.endswith('.'):
	else:
		return 'Whatever.'
