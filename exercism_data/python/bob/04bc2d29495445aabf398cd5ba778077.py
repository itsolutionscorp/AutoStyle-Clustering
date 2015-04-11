#! /usr/bin/env python
import sys
import string

def remove_blank( what_bob_say ):
	what_bob_say_blank = what_bob_say.replace(" ", "")
	what_bob_say_blank = what_bob_say_blank.replace("\t", "")
	what_bob_say_blank = what_bob_say_blank.replace("\s", "")
	return what_bob_say_blank


def bob_sure(what_bob_say_blank):
	pos = what_bob_say_blank.rfind("?")
	
	if what_bob_say_blank.isupper():
		return -1
		
	elif (len(what_bob_say_blank)-1) == pos and not len(what_bob_say_blank) == 0:
		return 1
		
	else:
		return -1
	

def bob_fine(what_bob_say_blank):
	if len(what_bob_say_blank) == 0:
		return 1
	else:
		return -1
		

def bob_whoa(what_bob_say_blank):
	if what_bob_say_blank.isupper():
		return 1
	else:
		return -1
		

def hey(what):
	
	what_bob_say_blank = remove_blank(what)
	#print(what_bob_say_blank+'\n')
			
	if bob_sure(what_bob_say_blank) > 0:
		return 'Sure.'
	
	elif bob_whoa(what_bob_say_blank) > 0:
		return 'Whoa, chill out!'
		
	elif bob_fine(what_bob_say_blank) > 0:
		return 'Fine. Be that way!'

	else:
	    return 'Whatever.'
