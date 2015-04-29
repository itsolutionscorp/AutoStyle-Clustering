# -*- coding: utf-8 -*-

from __future__ import unicode_literals
import re



def hey(what):

	if(not re.search(r"\w",what)):
		return "Fine. Be that way!"
	if(re.search(r"[0-9].*\?",what)):
		return "Sure."
	if(re.search(r"[0-9]$",what)):
		return "Whatever."
	if(not re.search(r"([a-zä])",what,re.U)):
		return "Whoa, chill out!"
	if(re.search(r"\?\s*$",what)):
		return "Sure."
	return "Whatever."
