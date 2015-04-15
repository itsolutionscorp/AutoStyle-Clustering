# -*- coding: utf-8 -*-
from __future__ import unicode_literals
#from string import *
def hey(what):
	l=len(what)
	if not(any(x.isupper() for x in what) or any(x.islower() for x in what) or any(x.isdigit() for x in what)):
		return 'Fine. Be that way!'
	elif (l>0):
		last_char=what[-1]
	if (what.isupper()):
		return 'Whoa, chill out!'	
	if (last_char=='?'):
		return 'Sure.'
	else:
		return 'Whatever.'
    
