# -*- coding: utf-8 -*-

import re

def hey(what):
	reply_dict = {
		'question': "Sure.",
		'shout': "Whoa, chill out!",
		'statement': "Whatever.",
		'empty': "Fine. Be that way!",
	}
	reply = None
	what = what.strip(' \t\n\r')
	unijoin = u''.join(what).encode('utf-8').strip()

	def is_shout(shout, result=None):
		strp = (' ', ',')
		pattern = r'([0-9]+)?([A-Z]{2,})([!?]$)?'
		shout = "".join(char for char in what if char not in strp)
		if re.match(pattern, shout):
			result = True
		else:
			result = False
		return result

	def is_statement(statement, result=None):
		strp = (' ', ',', '\'')
		pattern = r'(^([0-9]+$)|[A-Z]*[a-z]+)(\.*)'
		statement = "".join(char for char in what if char not in strp)

		if re.match(pattern, statement):
			result = True
		else:
			result = False
		return result

	if what.endswith("?") and not what.isupper():
		reply = reply_dict['question']
	elif is_statement(unijoin):
		reply = reply_dict['statement']
	elif is_shout(unijoin):
		reply = reply_dict['shout']
	elif what == "":
		reply = "Fine. Be that way!"

	return reply
