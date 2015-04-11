#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

	if( not(isinstance(what, basestring)) ):
		return 'Whatever.'
	# Strip string of leading and trailing whitespace
	what = what.strip()
	
	# He answers 'Whoa, chill out!' if you yell at him.
	# Check if all letters are capitalized. If so, respond 'Whoa, chill out!'
	if( what.isupper() ):
		return 'Whoa, chill out!'
		
	#Bob answers 'Sure.' if you ask him a question.
	# If the string ends in a question mark, then respond 'Sure.'
	if( what.endswith('?') ):
		return 'Sure.'

	# He says 'Fine. Be that way!' if you address him without actually saying
	# anything.
	if( len(what) == 0 or what.isspace() ):
		return 'Fine. Be that way!'
	# He answers 'Whatever.' to anything else.
	return 'Whatever.'
