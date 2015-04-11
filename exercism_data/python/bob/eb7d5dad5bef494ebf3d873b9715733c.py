# BOB

def hey(phrase):
	if len(phrase.rstrip(' \t\n\r'))==0:
		# Nothing said
		return 'Fine. Be that way!'
	if phrase.upper()==phrase and any(c.isalpha() for c in phrase):
		# Has letters and is all caps; Yelling
		return 'Woah, chill out!'
	if phrase.endswith('?'):
		# question Asked
		return 'Sure.'
		
	else:
		#Other case
		return 'Whatever.'
