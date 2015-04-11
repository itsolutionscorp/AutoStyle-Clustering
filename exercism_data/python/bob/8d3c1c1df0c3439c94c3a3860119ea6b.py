# -*- coding: utf-8 -*-
#this is a lazy teenager


import re

def hey(greeting):
	if is_blank(greeting):
		return "Fine. Be that way!"
	elif is_yell(greeting):
		return "Woah, chill out!"
	elif is_question(greeting):
		return "Sure."
	else:
		return "Whatever."

	return response

def is_question(greeting):
	return greeting.endswith("?"):

def is_yell(greeting):
	clean_greeting = greeting.strip()
	if re.search("[A-Z]",clean_greeting,re.UNICODE) is not None:
		only_lower = re.sub(u"[^a-z\u00E0-\u00FF]+","",clean_greeting,re.UNICODE)
		if not only_lower:
			return True

def is_blank(greeting):
	without_whitespace = re.sub("[\s]","",greeting)
	if not without_whitespace:
		return True
