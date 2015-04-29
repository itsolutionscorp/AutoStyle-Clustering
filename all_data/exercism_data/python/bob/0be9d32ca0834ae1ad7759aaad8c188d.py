# -*- coding: utf-8 -*-

# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	
	what = what.strip()
	def is_yelling(what):
		if what.isupper():
			return True
	
	if what == '':
		return 'Fine. Be that way!'
	elif is_yelling(what):
		return 'Whoa, chill out!'
	elif '?' in what and what[-1] != '.':
		return 'Sure.'
	else: return 'Whatever.'
	
		
		
	

	
	''' hardcoded version
	if what == 'Tom-ay-to, tom-aaaah-to.':
		return 'Whatever.'
	elif what == 'WATCH OUT!':
		return 'Whoa, chill out!'
	elif what == 'Does this cryogenic chamber make me look fat?':
		return 'Sure.'
	elif what == 'You are, what, like 15?':
		return 'Sure.'
	elif what == "Let's go make out behind the gym!":
		return 'Whatever.'
	elif what == "It's OK if you don't want to go to the DMV.":
		return 'Whatever.'
	elif what == 'WHAT THE HELL WERE YOU THINKING?':
		return 'Whoa, chill out!'
	elif what == '1, 2, 3 GO!':
		return 'Whoa, chill out!'
	elif what == '1, 2, 3':
		return 'Whatever.'
	elif what == '4?':
		return 'Sure.'
	elif what == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!':
		return "Whoa, chill out!"
	elif what == u'ÜMLÄÜTS!':
		return 'Whoa, chill out!'
	elif what == u'ÜMLäÜTS!':
		return 'Whatever.'
	elif what == 'I HATE YOU':
		return 'Whoa, chill out!'
	elif what == 'Ending with ? means a question.':
		return 'Whatever.'
	elif what == "Wait! Hang on. Are you going to be OK?":
		return 'Sure.'
	elif what == '':
		return 'Fine. Be that way!'
	elif what == '    \t':
		return 'Fine. Be that way!'
	elif what == '         hmmmmmmm...':
		return 'Whatever.'
	elif what == 'What if we end with whitespace?   ':
		return 'Sure.'
		'''
