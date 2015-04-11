# -*- coding: utf-8 -*-

def hey(string):
	if string.isupper() :
		return "Woah, chill out!"
	if string.endswith("?") :
		return "Sure."
	if len(string) == 0 or string.isspace() :
		return "Fine. Be that way!"
        return "Whatever."
