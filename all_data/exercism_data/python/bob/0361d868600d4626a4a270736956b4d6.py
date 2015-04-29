#
# Skeleton file for the Python "Bob" exercise.
#

# bob has limited responses
def hey(what):
	response='Whatever.'
	if is_silence(what):
		response='Fine. Be that way!'
	elif is_shouting(what):
		response='Whoa, chill out!'
	elif is_question(what):
		response='Sure.'
	return response

# shouting if the string is only uppercase
def is_shouting(what):
	return (what.isupper())

# silence if the string is only whitespace
def is_silence(what):
	return (what.isspace() or what=='')

# question means question mark at the end of the string
def is_question(what):
	return (what[-1]=='?')
