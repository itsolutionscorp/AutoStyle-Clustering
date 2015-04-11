# -*- coding: utf-8 -*-


def hey(s):
	# if string is uppercase, use yelling response
	if (s.isupper()):
		return "Whoa, chill out!"
	# ends in question mark, and is more than one character. 
	elif (len(s)> 1 and (s[len(s) - 1] == "?")):
		return "Sure."
	#if string is not alphanumeric, is spaces, or is empty
	elif (s.isspace() or len(s) == 0):
		return "Fine. Be that way!"
	# anything else, say Whatever
	else:
		return "Whatever."
		
