# -*- coding: utf-8 -*-

def hey(sentenceToBob):
	
	# if the last char of the sentence is a "?" we define it as a question
	# unless the sentence is in all upper case
	if sentenceToBob[-1:] == "?" and sentenceToBob.isupper() == False:
		sentenceToBob = "?"
	
	# if all chars are upper case we define it as shouting
	elif sentenceToBob.isupper():
		sentenceToBob = "!"
		
	# check if all chars are space, tabs or other whitespace
	elif sentenceToBob.isspace():
		sentenceToBob = ""
		
	return {
        ""  : "Fine. Be that way!",
        "?" : "Sure.",
        "!" : "Whoa, chill out!",
        }.get(sentenceToBob, "Whatever.")
        
