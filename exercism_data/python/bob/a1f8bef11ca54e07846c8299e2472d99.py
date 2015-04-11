# -*- coding: utf-8 -*-
# bob.py

def hey(statement):

	# Strip unneccesary whitespace.
	statement.strip()

	# Check if the string is only \n \t \r or empty.
	if (statement.isspace() or statement == ''):
		return 'Fine. Be that way!'
	
	# Check if the string is uppercase. (eg. Shouting)
	elif (statement.isupper()):
		return 'Woah, chill out!'

	# Check if the string ends with a questionmark. (eg. Question)
	elif (statement.endswith('?')):
		return 'Sure.'

	# By default, return 'Whatever.'.
	else:
		return 'Whatever.'
