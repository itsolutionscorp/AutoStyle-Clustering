#
# Skeleton file for the Python "Bob" exercise.
#

def hey(what):
	# check if all-caps
	if what.isupper():
		reply = "Whoa, chill out!"	
	# trim end whitespace and check if last char is ?
	elif what.rstrip().endswith('?'):
	   	reply = 'Sure.'
	# check if only whitespace
	elif len(what.rstrip()) == 0:
	#	alternative:
	# elif not (any(char.isalpha() for char in what) or any(char.isdigit() for char in what)):
		reply = "Fine. Be that way!"
	# check if no digits or letters	
	else:
		reply = "Whatever."

	return reply



print "What's up man?"
print hey(raw_input('>  '))
