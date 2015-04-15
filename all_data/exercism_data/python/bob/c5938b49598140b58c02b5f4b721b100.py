#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
def hey(what):

	# default response if nothing else fits
	response = 'Whatever.'

	if what.isupper():
		response = 'Whoa, chill out!'

	elif what.endswith('?'):
		response = 'Sure.'

	elif what.strip() == '' or what is None:
		response = 'Fine. Be that way!'

	return response
