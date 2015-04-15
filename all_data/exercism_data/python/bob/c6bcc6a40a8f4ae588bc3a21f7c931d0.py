# -*- coding: utf-8 -*-

def hey(sentence):
	if len(sentence) == 0 or sentence.isspace() :
		return 'Fine. Be that way!'
	elif sentence.isupper():
		return 'Whoa, chill out!'
	elif sentence[-1] == '?':
		return 'Sure.'
	else:
		return 'Whatever.'
