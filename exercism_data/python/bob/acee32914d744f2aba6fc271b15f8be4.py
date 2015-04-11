# -*- coding: utf-8 -*-


def hey(s):
	# if string is uppercase, use yelling response
	if (s.isupper()):
		return "Whoa, chill out!"
	# ends in question mark, and is more than one character.. maybe should contain a letter or number?
	elif (len(s)> 1 and (s[len(s) - 1] == "?")):
		return "Sure."
	#if string is not alphanumeric, is spaces, or is empty
	elif (s.isspace() or len(s) == 0):
		return "Fine. Be that way!"
	else:
		return "Whatever."
		
		
print hey('Tom-ay-to, tom-aaaah-to.')		
