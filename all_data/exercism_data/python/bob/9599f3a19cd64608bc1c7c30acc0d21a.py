#
# Skeleton file for the Python "Bob" exercise.
#
# Submitted by j00ce // 7 October 2014

import string

def hey(what):
	if len(what) == 0: #if string length is 0, you're saying nothing
		return 'Fine. Be that way!'
	elif len(what) > 0: #if string has content...
		if what.isspace(): #if string has only whitespace characters, no need for further tests
			return 'Fine. Be that way!'
		elif what[-1] == '?': #if not all whitespaces, then ending in ? is a necessary condition for a question
			if isGibberish(what): #if string is all numbers, whitespaces, and punctuation, it's gibberish
				return 'Sure.'
			elif isYelling(what): #...test to see if all caps -> all caps = YELLING
				return 'Whoa, chill out!'
			return 'Sure.' #if not gibberish and not YELLING, then it's a question
		elif isGibberish(what): #if string is all numbers, whitespaces, and punctuation, it's gibberish
			return 'Whatever.'
		elif isYelling(what): #if string doesn't end in ?, test to see if all caps -> all caps = YELLING
			return 'Whoa, chill out!'
	return 'Whatever.'

def isYelling(what): #checks to see if YELLING or talking forcefully
	for character in what:
		if character.isalpha(): #checks to see if the character is a letter
			if character.islower(): #for each letter, see if letter is upper or lower
				return False #yelling requires all letters to be in caps
	return True

def isGibberish(what): #checks to see if string is gibberish (only numbers, special chars, whitespaces)
	count = 0
	for character in what:
		if (character in string.digits or character in string.punctuation or character in string.whitespace):
			count += 1
	if count == len(what):
		return True
	return False
