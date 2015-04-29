#!/usr/bin/env python3 
import string

class bob:
	def hey(self, arg):
		nontext = str.maketrans("a","a","!@$#%^&*()., -_=+-")
		ntstrip = arg.translate(nontext)
		if ntstrip.isupper():
			return "Woah, chill out!"
		elif arg.endswith("?"):
			return "Sure."
		elif arg.isspace() or len(arg) == 0:
			return "Fine. Be that way!"
		else:
			return "Whatever."
