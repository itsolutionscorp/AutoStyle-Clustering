# -*- coding: utf-8 -*-
import re

def typeOfMessage(message):
	if re.search(r"[^\s]",message) == None: 
		return 'nothing'

	if re.search(u"[a-zåäöü]",message, re.UNICODE) == None and \
		re.search(r"^([0-9]*[.,? ]*)*$",message) == None:
		return 'yell'

	if re.search(r"\?$",message) != None:
		return 'question'

	return None

def hey(message):
	tom = 	typeOfMessage(message)

	if tom == 'question':
			return 'Sure.'
	elif tom == 'yell':
			return 'Whoa, chill out!'
	elif tom == 'nothing':
			return 'Fine. Be that way!'
	else:
			return 'Whatever.'

print hey("")
