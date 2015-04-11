# -*- coding: utf-8 -*-

def hey(string):
	"""
	Used to deliver the answers of Bob.
	"""
	if string.isupper() :
		return "Woah, chill out!"
	elif string.endswith("?") :
		return "Sure."
	elif not string or string.isspace() :
		return "Fine. Be that way!"
        return "Whatever."
