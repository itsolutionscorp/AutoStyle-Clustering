#
# Skeleton file for the Python "Bob" exercise.
#

from __future__ import unicode_literals

def is_exclamation(what):
	'''
	Returns true if all letters in string
	are uppercase; returns false otherwise
	'''
	if what == what.upper():
		if what.endswith("!"):
			return True
		return all(word.isalpha() for word in what.strip('?').split(' '))

	
def is_question(what):
	'''
	Returns true if what is a question;
	Returns false otherwise
	'''
	return what.endswith("?")
	

def hey(what):
	'''
	Simulates a lacadaisical teenager named
	Bob and his answers to various questions.
	'''
	if what is '' or what.isspace():
		return "Fine. Be that way!"
	if is_exclamation(what):
		return "Whoa, chill out!"
	if is_question(what):
		return "Sure."
	return "Whatever."
	
print hey("4?")
