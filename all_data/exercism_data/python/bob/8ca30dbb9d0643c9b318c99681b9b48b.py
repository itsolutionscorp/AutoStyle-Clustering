# -*- coding: utf-8 -*-

import string

def contains_letters(s):
	for c in s:
		if c not in string.punctuation + string.whitespace + string.digits:
			return True
	return False
			
def is_shouting(s):
	if contains_letters(s):
		for c in s:
			if c != c.upper():
				return False
		return True

	return False

def is_question(s):
	if '?' in s[-1]:
		return True
	return False

def is_nothing(s):
	if s.strip() == '':
		return True
	return False

def hey(s):
	if is_nothing(s):
		return 'Fine. Be that way!'

	if is_shouting(s):
		return 'Whoa, chill out!'

	if is_question(s):
		return 'Sure.'

	return 'Whatever.'
