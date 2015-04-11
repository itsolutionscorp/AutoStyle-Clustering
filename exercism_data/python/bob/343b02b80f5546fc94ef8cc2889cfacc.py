#
# Skeleton file for the Python "Bob" exercise.
#

import re

def is_yelling(what):
	"""
	>>> is_yelling("HELLO")
	True
	>>> is_yelling("no")
	False
	>>> is_yelling('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!')
	True
	"""
	none_numeric = re.sub(r'[0-9!?.,\s]', '', what)
	return none_numeric.isupper() if none_numeric else False

def is_question(what):
	"""
	>>> is_question("Hello?")
	True
	>>> is_question("Nope")
	False
	>>> is_question("Isn't he great? I think so")
	False
	"""
	return what[-1] == '?' if what else False

def is_addressing(what):
	return not bool(what.strip())

default = [(is_yelling, 'Whoa, chill out!'),
					 (is_question, 'Sure.'),
					 (is_addressing, 'Fine. Be that way!')]

def hey(what, expr_classifiers=default, default_resp='Whatever.'):
	"""
	expr_classifiers: (function, output) pair for a given rule.

	The list of expression classifiers is assumed to be in order of priority. So if
	two of the cases are met, the higher ranking will be chosen
	Take the classifiers as a list so they can be updated/changed given the circumstance.
	"""
	valid_responses = [expression for func, expression in expr_classifiers if func(what.strip())]
	return valid_responses[0] if valid_responses else default_resp
