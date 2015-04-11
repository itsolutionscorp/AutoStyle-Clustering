import re


def hey(greeting):
	
	#'Whoa, chill out!' if you yell at him.
	if greeting.isupper():
		
		return 'Whoa, chill out!'

	#Bob answers 'Sure.' if you ask him a question.
	elif greeting.endswith('?'):
		return 'Sure.'

	#'Fine. Be that way!' if you address him without actually saying anything
	elif greeting.isspace() or greeting == '':
		return 'Fine. Be that way!'


	#'Whatever.' to anything else.
	else:
		return 'Whatever.'
