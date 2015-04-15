#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	#Didn't say anyting
	if what == '' or what.isspace():
		return 'Fine. Be that way!'
	#Yelling
	if what.isupper():
		return 'Whoa, chill out!'
	#Question
	if what.endswith('?'):
		return 'Sure.'
	#Anything else
	return 'Whatever.'
