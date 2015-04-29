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
	found_lower, found_upper = False, False
	for c in what:
		if c.islower():
			found_lower = True
		elif c.isupper():
			found_upper = True
	return (not found_lower and found_upper)

# silence if the string is only whitespace
# (well, some whitespace)
def is_silence(what):
	what = what.replace(' ','')
	what = what.replace('\n','')
	what = what.replace('\r','')
	what = what.replace('\t','')
	return (what=='')

# question means question mark at the end of the string
def is_question(what):
	return (what[-1]=='?')
