# -*- coding: utf-8 -*-
#this is a lazy teenager


import re

def hey(greeting):
	response = ""
	if isBlank(greeting):
		response = "Fine. Be that way!"
	elif isYell(greeting):
		response = "Woah, chill out!"
	elif isQuestion(greeting):
		response = "Sure."
	else:
		response = "Whatever."

	return response

def isQuestion(greeting):
	clean_greeting = greeting.strip()
	response = False

	if clean_greeting.endswith("?"):
		response = True

	return response

def isYell(greeting):
	clean_greeting = greeting.strip()
	response = False
	if re.search("[A-Z]",clean_greeting,re.UNICODE) is not None:
		only_lower = re.sub(u"[^a-z\u00E0-\u00FF]+","",clean_greeting,re.UNICODE)
		if not only_lower:
			response = True
			
	return response

def isBlank(greeting):
	response = False
	without_whitespace = re.sub("[\s]","",greeting)
	if not without_whitespace:
		response = True

	return response
