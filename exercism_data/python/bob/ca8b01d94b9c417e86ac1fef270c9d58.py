#!/bin/python

def hey(msg):
	if not msg.strip():
		return 'Fine. Be that way!'
	isupper = msg.isupper()
	if msg[-1] == '!' and isupper or isupper:
		return 'Whoa, chill out!'
	if msg[-1] == '?':
		return 'Sure.'
	return 'Whatever.'
