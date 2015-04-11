# -*- coding: utf-8 -*-

def hey(words):

	if words is None or words == '' or words.isspace():
		return 'Fine. Be that way!'
	elif words.isupper():
		return 'Whoa, chill out!'
	elif '?' == words[len(words)-1]:
		return 'Sure.'
	else:
		return 'Whatever.'
	
			
	
