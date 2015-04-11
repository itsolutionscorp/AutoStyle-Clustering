#!/usr/bin/env python

def hey(s):
	s = s.strip()
	if len(s) == 0:
		return 'Fine. Be that way!'
	if s.isupper():
		return 'Whoa, chill out!'
	if s.endswith('?'):
		return 'Sure.'
	return 'Whatever.'
