#
# Skeleton file for the Python "Bob" exercise.
#

import re

def hey(what):
	if (what.upper() == what and re.match(r'.*[A-Z]+.*', what)):
	# if (re.match(r'^[A-Z]+$', what)):
		res = "Whoa, chill out!"
	elif (re.match(r'.*\?$', what)): 
		res = "Sure."
	elif (re.match(r'^\s*$', what)):
		res = "Fine. Be that way!"
	else:
		res = "Whatever."

	return res


#     Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.
