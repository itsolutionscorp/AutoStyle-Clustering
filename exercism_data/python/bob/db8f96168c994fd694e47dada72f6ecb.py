# -*- coding: utf-8 -*-
"""
Bob, the teenager.
"""

QUESTION_RESPONSE = 'Sure.'
YELL_RESPONSE = 'Whoa, chill out!'
EMPTY_RESPONSE = 'Fine. Be that way!'
DEFUALT_RESPONSE = 'Whatever.'

def hey(sentance):
	"""Responds to the sentance given.
	
	Possible responses:
	
	question -> 'Sure.'

	yelling -> 'Whoa, chill out!'

	empty sentance -> 'Fine. Be that way!'

	default response -> 'Whatever.'

	@type sentance: str
	@rtype str
	"""

	if not isinstance(sentance, basestring):
		# Return default response if the input
		# sentance is not an instance of basestring.
		return DEFUALT_RESPONSE

	# Remove special chars and trailing characters.
	sentance = sentance.strip()

	if not sentance:
		return EMPTY_RESPONSE		

	# A yelled question should be responed with 
	# a yell response.
	if _is_yell(sentance):
		return YELL_RESPONSE
	
	if _is_question(sentance):
		return QUESTION_RESPONSE

	return DEFUALT_RESPONSE


def _is_question(string):
	return string.endswith('?')

def _is_yell(string):
	return string.isupper()
