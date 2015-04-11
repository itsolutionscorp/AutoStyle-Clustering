# -*- coding: utf-8 -*-

question_answer = 'Sure.'
yell_answer = 'Whoa, chill out!'
nothing_answer = 'Fine. Be that way!'
default_answer = 'Whatever.'

def hey(text):
	if not text.strip():
		return nothing_answer
		
	if text.upper() is text and text.lower() is not text.upper():
		return yell_answer
	
	if text.endswith('?'):
		return question_answer
		
	return default_answer
