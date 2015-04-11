# -*- coding: utf-8 -*-

import re

#This function returns bob's answers when 
#someone speak to him
def hey(text):
	
	rex = re.compile('[\w]+')
	sr = rex.search(text)	


	if(not sr):
		return 'Fine. Be that way!'
	elif(text.isupper()):
		return 'Woah, chill out!'	
	elif(text[-1] == '?'):
		return 'Sure.'		
	else:
		return "Whatever."
