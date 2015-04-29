#
# 07 October  
# Code working to specifications
# Written by wollywave
#

def hey(what):

	# for using to check if string is not contaning letters or numbers
	import re
	pattern = r'[.a-z0-9A-Z]'

	if (what.isupper()): # person is screaming
		return("Whoa, chill out!")
	elif (what.endswith('?')): # person asks a question
		return("Sure.")
	elif not what: # person is not saying anything
		return("Fine. Be that way!")
	if not re.search(pattern, what): # person is not saying anything
		return("Fine. Be that way!")
	else: # anything else, whatever
		return("Whatever.")
