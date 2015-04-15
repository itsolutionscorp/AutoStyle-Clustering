# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(words):
	words = words.strip()
	
	if not words:
		return "Fine. Be that way!"
	if  words.isupper() :
		return 'Whoa, chill out!'
	if words.endswith('?'):
		return 'Sure.'

	
	return "Whatever."
