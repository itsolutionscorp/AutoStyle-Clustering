# -*- coding: utf-8 -*-

#2 FAILURES (umlauts and prattling)

#Regex to fix the errors and give the right responses

import re


def we():
	return 'Whatever.'
	
def sure():
	return 'Sure.'
	
def whoa():
	return 'Whoa, chill out!'
	
def fine():
	return 'Fine. Be that way!'

def hey(talk):
	
	if (re.search('.\.',talk)):
		
		return we()
	elif(re.search('[A-Z][A-Z]+',talk)):		
		return whoa()
		
	elif(re.search('.\!',talk)):
		return we()
		
	elif(re.search('.\?',talk)):
		return sure()
		
	elif(re.search('[0-9]',talk)):
		return we()
		
	elif(re.search('\s',talk) or talk == ''):
		return fine()
		
	elif(re.search('.\!.\..\?',talk)):
		return we()
