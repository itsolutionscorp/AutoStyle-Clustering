#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
def hey(what):
	response = ''

	# He answers 'Whoa, chill out!' if you yell at him.
	if what.isupper():
		response = 'Whoa, chill out!'

	# Bob answers 'Sure.' if you ask him a question.
	elif what.endswith('?'):
		response = 'Sure.'

	# He says 'Fine. Be that way!' if you address him without actually saying anything.
	elif what.strip() == '' or what is None:
		response = 'Fine. Be that way!'

	# He answers 'Whatever.' to anything else.
	else:
		response = 'Whatever.'

	return response
