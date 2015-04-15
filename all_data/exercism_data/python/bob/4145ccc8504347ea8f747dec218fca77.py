# -*- coding: utf-8 -*-
def hey(speech):
	if speech.isupper():
		return "Whoa, chill out!"
	elif speech.endswith('?'):
		return "Sure."
	elif speech=='':
		return "Fine. Be that way!"
	elif speech.strip("\t").isspace():
		return "Fine. Be that way!"
	else:
		return "Whatever."
