import re

def hey(phrase):
	if re.search('[A-Z][a-z]*\?', phrase):
		print 'Sure.'
	elif re.search('[A-Z][A-Z]*!', phrase):
		print 'Woah, chill out!'
	elif not phrase:
		print 'Fine. Be that way!' 
	else :
		print 'Whatever.'
