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

def is_question(greeting):
	return greeting.endswith("?")

def is_yell(greeting):
	return greeting.isupper()

def is_blank(greeting):
	return bool((not greeting) or greeting.isspace())
