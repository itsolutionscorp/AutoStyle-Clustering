#!/usr/bin/env python
# -*- coding: utf-8 -*-

def hey(phrase):
	if not isinstance(phrase, "".__class__):
		raise ValueError("phrase must be a string")
	
	if __is_empty_phrase(phrase):
		return "Fine. Be that way!" 
	elif __is_a_shout(phrase): 
		return "Whoa, chill out!"
	elif __is_a_question(phrase):
		return "Sure."
	else:
		return "Whatever."

def  __is_empty_phrase(phrase):
	return len(phrase.strip()) == 0
 
def __is_a_shout(phrase):
	return phrase.isupper()
			
def __is_a_question(phrase):
	trimmed = phrase.strip();
	return len(trimmed) > 0 and trimmed[-1] == "?"
