#!/usr/bin/env python
	
def hey(talk):
	talk = talk.strip()
	if not talk:
		return "Fine. Be that way!"
	elif talk.isupper():
		return "Woah, chill out!"
	elif talk.endswith('?'):
		return "Sure."
	else:
		return "Whatever."
	
