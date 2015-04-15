# -*- coding: utf-8 -*-
'''
Conversation with Bob.

Author: SprayIdle

This program implements the logic defined by the first problem (bob) in the
exercism.io python problem-set.

TESTING:

	bob_test.py (seperate file) will run numerous test cases over the 
	bob module.
	
REVISION HISTORY:

	24/09/14:	Initial implementation of hey function.
	24/09/14:	Add documentation and improved using examples on exercism.io
				as reference. Thanks to users Kiwwa and venfayth in particular.
'''

def hey(message):
	'''(string) -> string
	
	Return a string as a response to a message based on the following rules:
	
		- Bob answers 'Sure.' if you ask him a question.
		- He answers 'Whoa, chill out!' if you yell at him.
		- He says 'Fine. Be that way!' if you address him without actually 
		  saying anything.
		- He answers 'Whatever.' to anything else.
	'''
	
	# If message is empty or only whitespace return 'Fine. Be that way!'
	if message.strip() == '':
		return 'Fine. Be that way!'
	# If message is shouted return 'Whoa, chill out!', takes precedence over 
	# question.
	elif message.isupper():
		return 'Whoa, chill out!'
	# If message is question return 'Sure.'
	elif message.endswith('?'):
		return 'Sure.'
	# If message is anything else return 'Whatever.'
	else:
		return 'Whatever.'
