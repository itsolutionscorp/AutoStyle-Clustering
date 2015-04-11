# -*- coding: utf-8 -*-
from re import match

def hey(statement):
	if not statement.strip():
		return "Fine. Be that way!"

	if match("^[^a-zäöü]*[A-ZÄÜÖ]+[^a-zäöü]*$", statement):
		return "Woah, chill out!"

	if statement[-1] == "?":
		return "Sure."

	return "Whatever."
