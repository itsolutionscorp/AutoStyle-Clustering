#
# Solution to "Bob" exercise
#
# William Wenge-Murphy (https://github.com/BillyWM)
#
import re

def hey(what):

	# Rules:
	#	"yelling" is defined as all characters being uppercase.
	# 		- This also applies to unicode characters, such as uppercase characters with umulauts
	#		- yelling overrides asking a question, so "UPPERCASE?" should be answered with "chill out"

	all_whitespace = re.compile("^[\s]+$", re.UNICODE)
	contains_alpha = re.compile("[\w]+", re.UNICODE)
	contains_letters = re.compile("[^\s0-9!@#$%^&*()\-_=+\[\]{}|\\<>,./?`~]+", re.UNICODE);

	#First, catch strings that are just whitespace or completely empty
	if (what == '' or all_whitespace.match(what)):
		return 'Fine. Be that way!'
	
	#Next, catch shouting
	#A string is probably all uppercase if str.uppr() is identical to str.
	#	However, we need to verify that it actually contains word characters too,
	#	not just numbers and symbols
	elif (what == what.upper() and contains_letters.search(what)):
		return 'Whoa, chill out!'
		
	# Anything that isn't shouting and ends in a "?" is a question
	elif (what[-1:] == '?'):
		return 'Sure.'
	
	#Finally, catch anything else
	else:
		return 'Whatever.'
	
	return
