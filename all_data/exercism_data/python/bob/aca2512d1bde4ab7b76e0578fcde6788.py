# -*- coding: utf-8 -*-

#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
	conversation = what.strip()
	if (saying_anything(conversation)):
		return 'Fine. Be that way!';
	elif (question(conversation)):
		return 'Sure.'
	elif (yell(conversation)):
		return 'Whoa, chill out!'
	return 'Whatever.'

def saying_anything(conversation):
	return '' == conversation

def question(conversation):
	return ('?' == conversation[-1:]) and (re.search('[a-z]', conversation) or re.search('\d', conversation))

def yell(conversation):
	"""print(conversation)
	
	res1 = not re.search('[a-z]', conversation)
	res2 = re.search('[A-Z]', conversation)
	res3 = not re.search('[ä]', conversation)

	print(res1)
	print(res2)
	print(res3)
	print(10*'-')

	return res1 and res2 and res3"""
	return conversation.isSupper()
