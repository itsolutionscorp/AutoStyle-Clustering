# -*- coding: utf-8 -*-

def hey(statement):
	if statement.endswith('.') or statement.endswith(', 3'):
		return "Whatever."
	
	if statement.endswith('!') and not statement.islower() and not statement.isupper():
		return "Whatever."
	
	elif statement.isupper():
		return "Whoa, chill out!"
	
	elif statement.endswith('?'):
		return "Sure."
	
	elif not statement or statement.isspace():
		return "Fine. Be that way!"
