#
# Skeleton file for the Python "Bob" exercise.
#

"""
	Bob answers 'Sure.' if you ask him a question.
	He answers 'Whoa, chill out!' if you yell at him.
	He says 'Fine. Be that way!' if you address him without actually saying
	anything.
	He answers 'Whatever.' to anything else.

I need to list out all the expecations


	
"""
def hey(what):

	#if what ends in ?
	
	if what.endswith('?') and not what.isupper():
		return "Sure."
	elif what.endswith('!') and what.isupper():
 # if ends in ! OR 
		return "Whoa, chill out!"
	elif what.isupper():
		return "Whoa, chill out!"
	
	#if what contains no alphanum text

	
	elif what.isalnum(): ## wrong
###	what.isalnum(): #Return true if all characters in the string are alphanumeric and there is at least one character, false otherwise.
		return "Fine. Be that way!"

	elif what.isspace():
		
		return "Fine. Be that way!"
	elif len(what) == 0:
#	elif not what.isalpha() and not what.isalnum():
		return "Fine. Be that way!"
	
	else:	# catchall
		return "Whatever."

##	return
