# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import re

def hey(what):

	what = what.strip()
	splitedWhat = split(what)

	if what=='': return 'Fine. Be that way!'
	isAnShout= isShout(what)
	isDigit = isOnlyDigits(splitedWhat)
	
	if isDigit:
		if what[-1]=='?':
			return 'Sure.'
		else:
			return 'Whatever.'
	if isAnShout:
		return 'Whoa, chill out!'
	if what[-1]=='?':
		return 'Sure.'

	return 'Whatever.'
	
def isShout(what):
	if what==what.upper(): return True
	return False

def isOnlyDigits(what):
	for l in what:
		if l=='':continue
		if l.isdigit()==False: return False
	return True

def split(what):

	m = re.split("[,!?.\s]+",what)
	return m
