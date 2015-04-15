#!/usr/bin/python

#This is bob

def hey(statement):
	#Check to see if string is empty
	statement = statement.strip()
	if not statement:
		return u"Fine. Be that way!"	
	#If the statement is all uppercase then we consider it yelling
	elif statement.isupper():
		return u"Whoa, chill out!";
	#If the statement ends with a question mark it is a question
	elif statement.endswith('?'):
		return u"Sure.";
	else: 
		return u"Whatever.";
