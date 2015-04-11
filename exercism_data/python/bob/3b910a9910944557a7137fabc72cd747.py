#!/usr/bin/env python
# -*- coding: utf-8 -*-

import string

def is_all_caps(t):
	t = filter(lambda x: x not in string.digits and x not in string.punctuation, t)
	if is_empty(t):
		return False
	return t.upper().encode('UTF-8') == t.encode('UTF-8')

def is_empty(t):
	return filter(lambda x: x != ' ', t) == ''

def is_question(t):
	return t[-1] == '?' if len(t) > 0 else False

class Bob(object):

	def hey(self, text):
		response = ''
		if is_empty(text):
			return "Fine. Be that way!"
		elif is_all_caps(text):
			return "Woah, chill out!"
		elif is_question(text):
			return "Sure."
		else:
			return "Whatever."
