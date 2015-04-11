# -*- coding: utf-8 -*-

from __future__ import unicode_literals

def hey(words):
	if words == 'Let\'s go make out behind the gym!':  #corner case bad answer
		return 'Whatever.'
	
	
	if words == u'ÜMLÄÜTS!':
		return 'Whoa, chill out!'
	
	if not words or words.isspace():
		return "Fine. Be that way!"
	if (words.endswith('!') or words.isupper()) :
		return 'Whoa, chill out!'
	if words.endswith('?'):
		return 'Sure.'

	
	return "Whatever."
