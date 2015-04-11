# -*- coding: utf-8 -*-

def hey(x):
	original = x
	length = len(original)
	last = original[-1:]
	
	if length == 0 or original.isspace() == True:
		return 'Fine. Be that way!'
	elif original.isupper():
		return 'Woah, chill out!'
	elif last == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
