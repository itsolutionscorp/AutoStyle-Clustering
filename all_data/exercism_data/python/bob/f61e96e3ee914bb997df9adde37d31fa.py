# -*- coding: utf-8 -*-
"""
Bob, the teenager.
"""

QUESTION_RESPONSE = 'Sure.'
YELL_RESPONSE = 'Whoa, chill out!'
EMPTY_RESPONSE = 'Fine. Be that way!'
DEFUALT_RESPONSE = 'Whatever.'

def hey(sentence):
	"""Responds to the sentence given.
	
	Possible responses:
	
	question -> 'Sure.'

	yelling -> 'Whoa, chill out!'

	empty sentence -> 'Fine. Be that way!'

	default sentence -> 'Whatever.'

	@type sentence: str
	@rtype str
	"""

	if not isinstance(sentence, basestring):
		# Return default response if the input
		# sentence is not an instance of basestring.
		return DEFUALT_RESPONSE

	# Remove special chars and trailing characters.
	sentence = sentance.strip()

	if not sentence:
		return EMPTY_RESPONSE		

	# A yelled question should be responed with 
	# a yell response.
	if _is_yell(sentence):
		return YELL_RESPONSE
	
	if _is_question(sentence):
		return QUESTION_RESPONSE

	return DEFUALT_RESPONSE


def _is_question(string):
	return string.endswith('?')

def _is_yell(string):
	return string.isupper()
