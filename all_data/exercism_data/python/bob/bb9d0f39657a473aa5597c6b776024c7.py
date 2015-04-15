# -*- coding: utf-8 -*-

def hey(statement):
	
	if statement.isupper():
		return "Whoa, chill out!"
	
	elif statement.endswith('?'):
		return "Sure."
	
	elif not statement or statement.isspace():
		return "Fine. Be that way!"
	
	else:
		return 'Whatever.'
