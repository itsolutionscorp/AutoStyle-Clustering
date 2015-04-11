# -*- coding: utf-8 -*-
def hey( txt ):
	if is_silence(txt):
		return 'Fine. Be that way!'

	if is_yell(txt):
		return 'Whoa, chill out!'

	if is_question( txt ):
		return 'Sure.'

	return 'Whatever.'

def is_yell(txt):
	return txt.isupper()

def is_silence(txt):
	return txt.strip() == ''

def is_question(txt):
	return txt.endswith('?')
