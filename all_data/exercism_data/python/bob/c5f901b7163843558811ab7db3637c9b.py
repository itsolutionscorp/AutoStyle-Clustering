# -*- coding: utf-8 -*-

def hey(text):
	if (text.isspace() or text == ""):
		return("Fine. Be that way!")
	if (text.isupper()):
	    return("Woah, chill out!")
	if (text[-1] == "?"):
		return("Sure.")
	return("Whatever.")
