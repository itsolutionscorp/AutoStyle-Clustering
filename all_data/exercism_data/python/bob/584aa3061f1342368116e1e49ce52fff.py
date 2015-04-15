#
# Skeleton file for the Python "Bob" exercise.
#
import re

# A function that decides if a string has any letters at all
def has_letters( what ):
	return re.search( '[a-zA-Z]', what ) != None

def hey( what ):

	# Trim the input
	what = what.strip()
	# Check if the input has any letters
	is_alpha = has_letters( what )
	
	# Checking for an empty string
	if len( what ) == 0:
		return 'Fine. Be that way!'
	# Checking for a fully uppercase sentence
	elif is_alpha and what.upper() == what:
		return 'Whoa, chill out!'
	# Checking for question
	elif what[ -1 ] == '?':
		return 'Sure.'

	return 'Whatever.'
