import re
import string
#
# Skeleton file for the Python "Bob" exercise.
#

questions = ['do', 'what', 'why', 'does', 'is', 'are', 'am']

def hey(what):
	if what.isupper():
		return 'Whoa, chill out!'

	if is_it_a_question(what):
		return 'Sure.'

	if address_him_without_actually_saying_anything(what):
		return 'Fine. Be that way!'

	return 'Whatever.'

def address_him_without_actually_saying_anything(what):
	reg = re.compile('[a-zA-Z0-9]')
	return not reg.search(what)

def is_it_a_question(what):
	interrogation_in_the_end = is_there_interrogation_in_the_end(what)
	whatlowercase = string.lower(what)
	for question in questions:
		if whatlowercase.find(question) != -1 and interrogation_in_the_end:
			return True
		elif(interrogation_in_the_end):
			return True

def is_there_interrogation_in_the_end(what):
	# I tried to use what.strip().find('?') but not worked.
	stripwhat = what.strip()
	index = stripwhat.find('?')
	lastpositionindex = len(stripwhat) - 1
	if index != -1 and index == lastpositionindex:
		return True
