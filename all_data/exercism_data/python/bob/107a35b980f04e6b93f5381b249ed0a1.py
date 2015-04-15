# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import re

def hey( inPut ):

	if inPut == "":
		return "Fine. Be that way!"
	elif ''.join(inPut.split()) == "":
		return "Fine. Be that way!"

	endZeichen = inPut[-1]
	text = inPut[:-1]


	if not re.search( "[a-zA-Z]", text ):
		pass	
	elif text == text.upper():
		return "Whoa, chill out!"

	if endZeichen == "?":
		return "Sure."
	

	return "Whatever."
