# -*- coding: utf-8 -*-

def hey(words):
	if words is None or words.strip() == '':
		return 'Fine. Be that way!'
	elif words.isupper():
		return 'Whoa, chill out!'
	elif words.endswith('?'):
		return 'Sure.'
	else:
		return 'Whatever.'
	
			
	
