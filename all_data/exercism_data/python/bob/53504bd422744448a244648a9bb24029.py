# -*- coding: utf-8 -*-

def hey(question):
	if question.strip() == "":
		return "Fine. Be that way!"
	elif question.isupper():
		return "Whoa, chill out!"
	elif question[-1] == "?":
		return "Sure."
	else:
		return "Whatever."
