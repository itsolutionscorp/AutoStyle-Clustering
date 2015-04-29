#!/usr/bin/python -tt

def hey(input_str):
	
	if input_str.strip() == '': #removes all spaces/newline/... and check what is left
		return 'Fine. Be that way!'
	if input_str.isupper(): #check if string is all caps
		return 'Woah, chill out!'
	if input_str.endswith("?"): #check if question (yelling already eliminated)
		return 'Sure.'
	return 'Whatever.'
